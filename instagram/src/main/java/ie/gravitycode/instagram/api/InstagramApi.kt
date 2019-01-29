package ie.gravitycode.instagram.api

import ie.gravitycode.instagram.reponse.userinfo.UserInfo
import ie.gravitycode.instagram.reponse.userprofile.UserProfile
import io.reactivex.Observable
import retrofit2.http.GET

const val ACCESS_TOKEN = "4464978994.5f2c2db.3e032e769b4b41ebb000637dde05f26d"

interface InstagramApi {

    @GET("self/?access_token=$ACCESS_TOKEN")
    fun getUserInfo(): Observable<UserInfo>

    @GET("self/media/recent?access_token=$ACCESS_TOKEN")
    fun getUserProfile(): Observable<UserProfile>
}