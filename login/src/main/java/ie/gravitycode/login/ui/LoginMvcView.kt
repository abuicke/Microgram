package ie.gravitycode.login.ui

import ie.gravitycode.core.mvc.MvcView
import io.reactivex.Observable

interface LoginMvcView : MvcView {

    fun subscribeLoginClicked(): Observable<Unit>

    fun showLoginFailedMessage()

}