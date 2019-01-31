package ie.gravitycode.instagram.api

import ie.gravitycode.instagram.reponse.userinfo.UserInfo
import ie.gravitycode.instagram.reponse.userprofile.UserProfile
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface InstagramApi {

    @GET("self/")
    fun getUserInfo(@Query("access_token") accessToken: String): Observable<UserInfo>

    @GET("self/media/recent")
    fun getUserProfile(@Query("access_token") accessToken: String): Observable<UserProfile>
}