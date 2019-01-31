package ie.gravitycode.userprofile

import ie.gravitycode.core.mvc.MvcView

interface UserProfileMvcView : MvcView {

    fun setUsername(username: String)

    fun setProfilePicture(profilePicture: String)

    fun setPostsCount(postsCount: Long)

    fun setFollowersCount(followersCount: Long)

    fun setFollowingCount(followingCount: Long)

    fun setFirstName(firstName: String)

    fun setPostedImage(uri: String, index: Int)
}