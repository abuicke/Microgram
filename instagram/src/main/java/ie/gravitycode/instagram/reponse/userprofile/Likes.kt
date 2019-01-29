package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("count") val count: Long
)
