package ie.gravitycode.microgram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.microgram.dependencyinjection.AppModule
import ie.gravitycode.microgram.dependencyinjection.DaggerAppComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val appComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()

    @Inject internal lateinit var instagramApi: InstagramApi
    @Inject internal lateinit var mvcViewFactory: MvcViewFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        val loginMvcView = mvcViewFactory.getLoginMvcView(null)
        loginMvcView.subscribeLoginComplete()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<String>() {
                override fun onComplete() {

                }

                override fun onNext(authToken: String) {
                    val userProfileMvcView =
                        mvcViewFactory.getUserProfileMvcView(null)
                    userProfileMvcView.setUsername("JayJayBuick")
                    setContentView(userProfileMvcView.getRootView())
                }

                override fun onError(e: Throwable) {

                }
            })

        setContentView(loginMvcView.getRootView())
    }

}
