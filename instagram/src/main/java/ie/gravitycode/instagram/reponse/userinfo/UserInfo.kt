package ie.gravitycode.instagram.reponse.userinfo

import com.google.gson.annotations.SerializedName
import ie.gravitycode.instagram.reponse.common.Meta

data class UserInfo(
    @SerializedName("data") val data: Data,
    @SerializedName("meta") val meta: Meta
)