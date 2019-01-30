package ie.gravitycode.microgram

import android.os.Bundle
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

    private val activityComponent = DaggerActivityComponent
        .builder()
        .mvcViewModule(MvcViewModule(this))
        .build()

    @Inject internal lateinit var instagramApi: InstagramApi
    @Inject internal lateinit var mvcViewFactory: MvcViewFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        val loginMvcView = mvcViewFactory.getLoginMvcView(null)
        loginMvcView.subscribeLoginComplete()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<String>() {
                override fun onComplete() {

                }

                override fun onNext(authToken: String) {
                    instagramApi.getUserInfo(authToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : DisposableObserver<UserInfo>() {
                            override fun onComplete() {

                            }

                            override fun onNext(userInfo: UserInfo) {
                                val userProfileMvcView =
                                    mvcViewFactory.getUserProfileMvcView(null)
                                userProfileMvcView.setUsername(userInfo.data.username)
                                setContentView(userProfileMvcView.getRootView())
                            }

                            override fun onError(e: Throwable) {

                            }
                        })
                }

                override fun onError(e: Throwable) {

                }
            })

        setContentView(loginMvcView.getRootView())
    }

}
