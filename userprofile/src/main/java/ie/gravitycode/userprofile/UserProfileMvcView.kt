package ie.gravitycode.userprofile

import ie.gravitycode.core.mvc.MvcView

interface UserProfileMvcView: MvcView {

    fun setUsername(username: String)
}