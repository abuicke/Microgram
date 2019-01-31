package ie.gravitycode.microgram

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.instagram.reponse.userinfo.UserInfo
import ie.gravitycode.microgram.dependencyinjection.DaggerActivityComponent
import ie.gravitycode.microgram.dependencyinjection.MvcViewModule
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

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        loginMvcView.subscribeLoginClicked()
            .switchMap {
                setContentView(instagramOAuthMvcView.getRootView())
                instagramOAuthMvcView.startSignIn()
            }
            .observeOn(Schedulers.io())
            .switchMap { auth -> instagramApi.getUserInfo(auth.getAccessToken()) }
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

}
