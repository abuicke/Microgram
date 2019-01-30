package ie.gravitycode.microgram

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.instagram.reponse.userprofile.UserProfile
import ie.gravitycode.login.ui.LoginMvcView
import ie.gravitycode.login.ui.LoginMvcViewImpl
import ie.gravitycode.microgram.common.MicrogramApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject internal lateinit var instagramApi: InstagramApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MicrogramApp)
            .getAppComponent()
            .inject(this)

        val loginMvcView: LoginMvcView = LoginMvcViewImpl(layoutInflater, null)
        loginMvcView.subscribeLoginComplete()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<String>() {
                override fun onComplete() {

                }

                override fun onNext(authToken: String) {
                    instagramApi.getUserProfile()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : DisposableObserver<UserProfile>() {
                            override fun onComplete() {

                            }

                            override fun onNext(userProfile: UserProfile) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Logged in as ${userProfile.data[0].likes.count}",
                                    Toast.LENGTH_LONG
                                ).show()
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
