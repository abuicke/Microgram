package ie.gravitycode.microgram

import android.view.ViewGroup
import ie.gravitycode.login.ui.LoginMvcView
import ie.gravitycode.login.ui.oauth.InstagramOAuthMvcView
import ie.gravitycode.userprofile.UserProfileMvcView

interface MvcViewFactory {

    fun getLoginMvcView(parent: ViewGroup?): LoginMvcView

    fun getInstagramOAuthMvcView(parent: ViewGroup?): InstagramOAuthMvcView

    fun getUserProfileMvcView(parent: ViewGroup?): UserProfileMvcView

}
