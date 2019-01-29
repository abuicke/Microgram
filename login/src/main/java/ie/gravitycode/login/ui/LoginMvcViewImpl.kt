package ie.gravitycode.login.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ie.gravitycode.login.R

class LoginMvcViewImpl(context: Context, parent: ViewGroup?) : LoginMvcView {

    private val rootView: View

    init {
        val inflater = LayoutInflater.from(context)
        rootView = inflater.inflate(R.layout.login_screen, parent, false)
    }

    override fun getRootView() = rootView
}