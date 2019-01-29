package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class UsersInPhoto(
    @SerializedName("userInPhoto") val userInPhoto: UserInPhoto,
    @SerializedName("position") val position: Position
)
