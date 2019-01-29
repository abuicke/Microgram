package ie.gravitycode.microgram

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.instagram.reponse.userprofile.UserProfile
import ie.gravitycode.login.ui.LoginMvcViewImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.instagram.com/v1/users/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        val instagramApi = retrofit.create(InstagramApi::class.java)
        instagramApi.getUserProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<UserProfile>() {
                override fun onComplete() {

                }

                override fun onNext(userProfile: UserProfile) {
                    Toast.makeText(this@MainActivity,
                        "Logged in as ${userProfile.data[0].likes.count}", Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Throwable) {

                }
            })


        val loginMvcView = LoginMvcViewImpl(this, null)
        setContentView(loginMvcView.getRootView())
    }

}
