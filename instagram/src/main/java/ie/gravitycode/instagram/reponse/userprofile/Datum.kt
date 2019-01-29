package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("id") val id: String,
    @SerializedName("userInPhoto") val user: User,
    @SerializedName("images") val images: Images,
    @SerializedName("created_time") val createdTime: String,
    @SerializedName("caption") val caption: Caption,
    @SerializedName("user_has_liked") val userHasLiked: Boolean,
    @SerializedName("likes") val likes: Likes,
    @SerializedName("tags") val tags: List<Any>,
    @SerializedName("filter") val filter: String,
    @SerializedName("comments") val comments: Comments,
    @SerializedName("type") val type: String,
    @SerializedName("link") val link: String,
    @SerializedName("location") val location: Location,
    @SerializedName("attribution") val attribution: Any,
    @SerializedName("users_in_photo") val usersInPhoto: List<UsersInPhoto>,
    @SerializedName("carousel_media") val carouselMedia: List<CarouselMedium>
)
