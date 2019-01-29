package ie.gravitycode.instagram.reponse.userinfo

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("profile_picture") val profilePicture: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("bio") val bio: String,
    @SerializedName("website") val website: String,
    @SerializedName("is_business") val isBusiness: Boolean,
    @SerializedName("counts") val counts: Counts
)