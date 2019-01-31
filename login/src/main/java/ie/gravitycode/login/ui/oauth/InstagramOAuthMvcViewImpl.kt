package ie.gravitycode.login.ui.oauth

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import butterknife.ButterKnife
import ie.gravitycode.login.R
import ie.gravitycode.login.R2
import ie.gravitycode.login.util.Authentication
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.net.URI

@SuppressLint("SetJavaScriptEnabled")
class InstagramOAuthMvcViewImpl(inflater: LayoutInflater, parent: ViewGroup?) :
    InstagramOAuthMvcView {

    private val loginSubject: Subject<Authentication> = PublishSubject.create()

    private val rootView: View = inflater.inflate(R.layout.web_view, parent, false)
    @BindView(R2.id.web_view) internal lateinit var webView: WebView

    init {
        ButterKnife.bind(this, rootView)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.webViewClient = object : WebViewClient() {

            @Suppress("OverridingDeprecatedMember")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains("#access_token")) {
                    val accessTokenFragment = URI(url).fragment
                    val accessToken = accessTokenFragment.removePrefix("access_token=")
                    loginSubject.onNext(object : Authentication {
                        override fun getAccessToken() = accessToken
                    })
                } else {
                    view.loadUrl(url)
                }

                return true
            }

            override fun onPageFinished(view: WebView, url: String) {

            }
        }
    }

    override fun getRootView() = rootView

    override fun startSignIn(): Observable<Authentication> {
        webView.loadUrl(
            "https://www.instagram.com/oauth/authorize/?" +
                    "client_id=5f2c2dbc8809457faaddd7c3834ff8ba&" +
                    "redirect_uri=https://google.com&" +
                    "response_type=token&" +
                    "scope=public_content"
        )

        return loginSubject
    }

}