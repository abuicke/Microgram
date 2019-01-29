package ie.gravitycode.microgram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.gravitycode.login.ui.LoginMvcViewImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginMvcView = LoginMvcViewImpl(this, null)
        setContentView(loginMvcView.getRootView())
    }
}
