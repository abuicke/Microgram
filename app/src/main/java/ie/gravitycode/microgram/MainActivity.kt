package ie.gravitycode.microgram

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ie.gravitycode.instagram.api.InstagramApi
import ie.gravitycode.instagram.reponse.userprofile.UserProfile
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

        val loginMvcView = LoginMvcViewImpl(this, null)
        setContentView(loginMvcView.getRootView())

//        webView.settings.javaScriptEnabled = true
//        webView.settings.loadWithOverviewMode = true
//        webView.settings.useWideViewPort = true
//        webView.webViewClient = object : WebViewClient() {
//
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                if(url.contains("#access_token")) {
//                    Log.e("mo", "fragment = ${URI(url).fragment}")
//                    view.visibility = View.GONE
//                }else {
//                    view.loadUrl(url)
//                }
//
//                return true
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//
//            }
//        }
//
//        webView.loadUrl(
//            "https://www.instagram.com/oauth/authorize/?" +
//                    "client_id=5f2c2dbc8809457faaddd7c3834ff8ba&" +
//                    "redirect_uri=https://google.com&" +
//                    "response_type=token&" +
//                    "scope=public_content"
//        )
    }

}
