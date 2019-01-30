package ie.gravitycode.userprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

class UserProfileMvcViewImpl(inflater: LayoutInflater, parent: ViewGroup?) : UserProfileMvcView {

    private val rootView: View = inflater.inflate(R.layout.user_profile_screen, parent, false)
    @BindView(R2.id.username) internal lateinit var usernameView: TextView

    init {
        ButterKnife.bind(this, rootView)
    }

    override fun getRootView() = rootView

    override fun setUsername(username: String) {
        usernameView.text = username
    }
}