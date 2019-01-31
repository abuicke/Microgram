package ie.gravitycode.microgram

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.instagram.reponse.userinfo.UserInfo
import ie.gravitycode.login.util.Authentication
import ie.gravitycode.microgram.dependencyinjection.DaggerActivityComponent
import ie.gravitycode.microgram.dependencyinjection.MvcViewModule
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        val storedAuthSource = getStoredAccessToken()
            .toObservable()

        val userLoginAuthSource =
            loginMvcView.subscribeLoginClicked()
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    setContentView(instagramOAuthMvcView.getRootView())
                    instagramOAuthMvcView.startSignIn()
                }

        Observable.concat(storedAuthSource, userLoginAuthSource)
            .observeOn(Schedulers.io())
            .firstElement()
            .flatMapObservable { auth ->
                val accessToken = auth.getAccessToken()
                storeAccessToken(accessToken)
                instagramApi.getUserInfo(accessToken)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<UserInfo>() {
                override fun onComplete() {
                    dispose()
                }

                override fun onNext(userInfo: UserInfo) {
                    userProfileMvcView.setUsername(userInfo.data.username)
                    setContentView(userProfileMvcView.getRootView())
                    dispose()
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "login failed", e)
                    loginMvcView.showLoginFailedMessage()
                    dispose()
                }
            })

        setContentView(loginMvcView.getRootView())
    }

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

}
