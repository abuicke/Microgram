package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Caption(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
    @SerializedName("created_time") val createdTime: String,
    @SerializedName("from") val from: From
)
