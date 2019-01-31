package ie.gravitycode.login.ui.oauth

import ie.gravitycode.core.mvc.MvcView
import ie.gravitycode.login.util.Authentication
import io.reactivex.Observable

interface InstagramOAuthMvcView : MvcView {

    fun startSignIn(): Observable<Authentication>
}