package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class UserInPhoto(
    @SerializedName("username") val username: String
)
