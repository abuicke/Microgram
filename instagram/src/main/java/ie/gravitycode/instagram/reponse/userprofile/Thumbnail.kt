package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("width") val width: Long,
    @SerializedName("height") val height: Long,
    @SerializedName("url") val url: String
)
