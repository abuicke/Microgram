package ie.gravitycode.microgram

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.instagram.reponse.userinfo.UserInfo
import ie.gravitycode.instagram.reponse.userprofile.UserProfile
import ie.gravitycode.login.util.Authentication
import ie.gravitycode.microgram.dependencyinjection.DaggerActivityComponent
import ie.gravitycode.microgram.dependencyinjection.MvcViewModule
import ie.gravitycode.microgram.mvc.MvcViewFactory
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private val activityComponent = DaggerActivityComponent
        .builder()
        .mvcViewModule(MvcViewModule(this))
        .build()

    @Inject internal lateinit var instagramApi: InstagramApi
    @Inject internal lateinit var mvcViewFactory: MvcViewFactory

    private val loginMvcView by lazy { mvcViewFactory.getLoginMvcView(null) }
    private val instagramOAuthMvcView by lazy { mvcViewFactory.getInstagramOAuthMvcView(null) }
    private val userProfileMvcView by lazy { mvcViewFactory.getUserProfileMvcView(null) }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        authenticate()
            .flatMap { auth ->
                val accessToken = auth.getAccessToken()
                Observable.zip(
                    instagramApi.getUserInfo(accessToken),
                    instagramApi.getUserProfile(accessToken),
                    BiFunction<UserInfo, UserProfile, Pair<UserInfo, UserProfile>> { userInfo, userProfile ->
                        Pair(userInfo, userProfile)
                    })
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userInfoAndProfile ->
                    showUserProfile(userInfoAndProfile.first, userInfoAndProfile.second)
                },
                { e ->
                    Log.e(TAG, "login failed", e)
                    loginMvcView.showLoginFailedMessage()
                })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sign_out -> {
                signOut()
            }
        }
        return true
    }

    private fun authenticate(): Observable<Authentication> {
        if (!hasStoredAccessToken()) {
            setContentView(loginMvcView.getRootView())
        }

        return getStoredAccessToken()
            .toObservable()
            .concatWith(loginMvcView.subscribeLoginClicked()
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    setContentView(instagramOAuthMvcView.getRootView())
                    instagramOAuthMvcView.startSignIn()
                })
            .observeOn(Schedulers.io())
            .flatMap { auth ->
                storeAccessToken(auth.getAccessToken())
                Observable.just(auth)
            }
    }

    private fun hasStoredAccessToken() =
        getPreferences(MODE_PRIVATE)
            .contains("access_token")

    private fun storeAccessToken(accessToken: String) {
        getPreferences(MODE_PRIVATE).edit {
            putString("access_token", accessToken)
            apply()
        }
    }

    private fun getStoredAccessToken(): Maybe<Authentication> {
        val accessToken = getPreferences(MODE_PRIVATE)
            .getString("access_token", null)
        return if (accessToken == null) {
            Maybe.empty<Authentication>()
        } else {
            Maybe.just(object : Authentication {
                override fun getAccessToken() = accessToken
            })
        }
    }

    private fun showUserProfile(userInfo: UserInfo, userProfile: UserProfile) {
        userProfileMvcView.setUsername(userInfo.data.username)
        userProfileMvcView.setProfilePicture(userInfo.data.profilePicture)
        userProfileMvcView.setPostsCount(userInfo.data.counts.media)
        userProfileMvcView.setFollowersCount(userInfo.data.counts.followedBy)
        userProfileMvcView.setFollowingCount(userInfo.data.counts.follows)
        userProfileMvcView.setFirstName(userInfo.data.fullName)

        val userProfileView = userProfileMvcView.getRootView()
        val toolbar: Toolbar = userProfileView.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setContentView(userProfileView)

        for (i in 0 until userProfile.data.size) {
            userProfileMvcView.setPostedImage(userProfile.data[i].images.thumbnail.url, i)
        }
    }

    private fun signOut() {
        getPreferences(MODE_PRIVATE).edit {
            remove("access_token")
            apply()
        }
        setContentView(loginMvcView.getRootView())
    }

}
