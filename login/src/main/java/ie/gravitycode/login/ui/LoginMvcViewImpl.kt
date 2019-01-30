package ie.gravitycode.login.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import ie.gravitycode.login.R
import ie.gravitycode.login.R2
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.net.URI

class LoginMvcViewImpl(inflater: LayoutInflater, parent: ViewGroup?) : LoginMvcView {

    private val loginCompleteSubject: Subject<String> = PublishSubject.create()

    private val rootView: View = inflater.inflate(R.layout.login_screen, parent, false)
    @BindView(R2.id.web_view) internal lateinit var webView: WebView
    @BindView(R2.id.login_button) internal lateinit var loginButton: Button

    init {
        ButterKnife.bind(this, rootView)
    }

    override fun getRootView() = rootView

    override fun subscribeLoginComplete() = loginCompleteSubject

    @OnClick(R2.id.login_button)
    internal fun login() = showWebView()

    @SuppressLint("SetJavaScriptEnabled")
    private fun showWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains("#access_token")) {
                    val authToken = URI(url).fragment
                    loginCompleteSubject.onNext(authToken)
                    view.visibility = View.GONE
                } else {
                    view.loadUrl(url)
                }

                return true
            }

            override fun onPageFinished(view: WebView, url: String) {

            }
        }

        webView.visibility = View.VISIBLE
        webView.loadUrl(
            "https://www.instagram.com/oauth/authorize/?" +
                    "client_id=5f2c2dbc8809457faaddd7c3834ff8ba&" +
                    "redirect_uri=https://google.com&" +
                    "response_type=token&" +
                    "scope=public_content"
        )
    }

}