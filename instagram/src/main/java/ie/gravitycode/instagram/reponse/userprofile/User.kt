package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("profile_picture") val profilePicture: String,
    @SerializedName("username") val username: String
)
