package ie.gravitycode.userprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import ie.gravitycode.core.util.ImageLoader

class UserProfileMvcViewImpl(
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    parent: ViewGroup?
) : UserProfileMvcView {

    private val rootView: View = inflater.inflate(R.layout.user_profile_screen, parent, false)
    @BindView(R2.id.toolbar_username) internal lateinit var toolbarUsernameView: TextView
    @BindView(R2.id.profile_picture) internal lateinit var profilePictureView: ImageView
    @BindView(R2.id.posts_stat) internal lateinit var postsStatView: TextView
    @BindView(R2.id.followers_stat) internal lateinit var followersStatView: TextView
    @BindView(R2.id.following_stat) internal lateinit var followingStatView: TextView

    init {
        ButterKnife.bind(this, rootView)
    }

    override fun getRootView() = rootView

    override fun setUsername(username: String) {
        toolbarUsernameView.text = username
    }

    override fun setProfilePicture(profilePicture: String) {
        imageLoader.load(profilePicture, profilePictureView)
    }

    override fun setPostsCount(postsCount: Long) {
        postsStatView.text = postsCount.toString()
    }

    override fun setFollowersCount(followersCount: Long) {
        followersStatView.text = followersCount.toString()
    }

    override fun setFollowingCount(followingCount: Long) {
        followingStatView.text = followingCount.toString()
    }
}