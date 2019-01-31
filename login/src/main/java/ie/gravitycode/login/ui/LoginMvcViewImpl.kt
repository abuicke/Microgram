package ie.gravitycode.login.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import ie.gravitycode.core.util.ToastManager
import ie.gravitycode.login.R
import ie.gravitycode.login.R2
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class LoginMvcViewImpl(
    inflater: LayoutInflater,
    private val toastManager: ToastManager,
    parent: ViewGroup?
) : LoginMvcView {

    private val loginClickedSubject: Subject<Unit> = PublishSubject.create()

    private val rootView: View = inflater.inflate(R.layout.login_screen, parent, false)
    @BindView(R2.id.login_button) internal lateinit var loginButton: Button

    init {
        ButterKnife.bind(this, rootView)
    }

    override fun getRootView() = rootView

    override fun subscribeLoginClicked() = loginClickedSubject

    override fun showLoginFailedMessage() = toastManager.toastLong("Login failed")

    @OnClick(R2.id.login_button)
    internal fun login() = loginClickedSubject.onNext(Unit)

}