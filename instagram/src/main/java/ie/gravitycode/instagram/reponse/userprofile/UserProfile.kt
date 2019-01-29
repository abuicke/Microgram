package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName
import ie.gravitycode.instagram.reponse.common.Meta

data class UserProfile(
    @SerializedName("pagination") val pagination: Any,
    @SerializedName("data") val data: List<Datum>,
    @SerializedName("meta") val meta: Meta
)
