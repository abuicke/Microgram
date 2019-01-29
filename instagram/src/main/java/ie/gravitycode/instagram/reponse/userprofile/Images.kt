package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("low_resolution") val lowResolution: LowResolution,
    @SerializedName("standard_resolution") val standardResolution: StandardResolution
)
