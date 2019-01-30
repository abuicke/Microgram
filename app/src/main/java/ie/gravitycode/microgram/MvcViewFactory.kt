package ie.gravitycode.microgram

import android.view.ViewGroup
import ie.gravitycode.login.ui.LoginMvcView
import ie.gravitycode.userprofile.UserProfileMvcView

interface MvcViewFactory {

    fun getLoginMvcView(parent: ViewGroup?): LoginMvcView

    fun getUserProfileMvcView(parent: ViewGroup?): UserProfileMvcView

}
