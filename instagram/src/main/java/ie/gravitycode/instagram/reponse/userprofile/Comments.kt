package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("count") val count: Long
)
