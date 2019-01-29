package ie.gravitycode.instagram.reponse.userinfo

import com.google.gson.annotations.SerializedName

data class Counts(
    @SerializedName("media") val media: Long,
    @SerializedName("follows") val follows: Long,
    @SerializedName("followedBy") val followedBy: Long
)
