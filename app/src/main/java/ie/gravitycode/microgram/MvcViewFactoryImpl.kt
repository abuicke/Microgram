package ie.gravitycode.microgram

import android.view.LayoutInflater
import android.view.ViewGroup
import ie.gravitycode.login.ui.LoginMvcViewImpl
import ie.gravitycode.userprofile.UserProfileMvcViewImpl

class MvcViewFactoryImpl(private val inflater: LayoutInflater) : MvcViewFactory {

    override fun getLoginMvcView(parent: ViewGroup?) =
        LoginMvcViewImpl(inflater, parent)

    override fun getUserProfileMvcView(parent: ViewGroup?) =
        UserProfileMvcViewImpl(inflater, parent)

}