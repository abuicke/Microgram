package ie.gravitycode.microgram

import android.view.LayoutInflater
import android.view.ViewGroup
import ie.gravitycode.core.util.ToastManager
import ie.gravitycode.login.ui.LoginMvcViewImpl
import ie.gravitycode.login.ui.oauth.InstagramOAuthMvcViewImpl
import ie.gravitycode.userprofile.UserProfileMvcViewImpl

class MvcViewFactoryImpl(
    private val inflater: LayoutInflater,
    private val toastManager: ToastManager
) : MvcViewFactory {

    override fun getLoginMvcView(parent: ViewGroup?) =
        LoginMvcViewImpl(inflater, toastManager, parent)

    override fun getInstagramOAuthMvcView(parent: ViewGroup?) =
        InstagramOAuthMvcViewImpl(inflater, parent)

    override fun getUserProfileMvcView(parent: ViewGroup?) =
        UserProfileMvcViewImpl(inflater, parent)

}