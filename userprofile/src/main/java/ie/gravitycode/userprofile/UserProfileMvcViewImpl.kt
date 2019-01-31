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
    private val inflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    parent: ViewGroup?
) : UserProfileMvcView {

    private val rootView: View = inflater.inflate(R.layout.user_profile_screen, parent, false)
    @BindView(R2.id.toolbar_username) internal lateinit var toolbarUsernameView: TextView
    @BindView(R2.id.profile_picture) internal lateinit var profilePictureView: ImageView
    @BindView(R2.id.posts_stat) internal lateinit var postsStatView: TextView
    @BindView(R2.id.followers_stat) internal lateinit var followersStatView: TextView
    @BindView(R2.id.following_stat) internal lateinit var followingStatView: TextView
    @BindView(R2.id.first_name) internal lateinit var firstNameView: TextView
    @BindView(R2.id.posted_images_container) internal lateinit var postedImagesContainerView: ViewGroup

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

    override fun setFirstName(firstName: String) {
        firstNameView.text = firstName
    }

    override fun setPostedImage(uri: String, index: Int) {
        if (index >= postedImagesContainerView.childCount * 3) {
            inflater.inflate(R.layout.images_row, postedImagesContainerView, true)
        }

        val row = index / 3
        val position = index % 3

        val rowViewGroup: ViewGroup = postedImagesContainerView.getChildAt(row) as ViewGroup
        val imageView = rowViewGroup.findViewById<ImageView>(
            when (position) {
                0 -> R.id.left_image
                1 -> R.id.centre_image
                2 -> R.id.right_image
                else -> {
                    if (BuildConfig.DEBUG) {
                        throw IllegalStateException("unexpected row position $position")
                    }
                    R.id.left_image
                }
            }
        )

        imageLoader.load(uri, imageView)
    }
}