package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class CarouselMedium(
    @SerializedName("images") val images: Images,
    @SerializedName("users_in_photo") val usersInPhoto: List<Any>,
    @SerializedName("type") val type: String
)
