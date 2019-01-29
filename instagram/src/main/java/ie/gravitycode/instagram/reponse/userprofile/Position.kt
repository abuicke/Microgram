package ie.gravitycode.instagram.reponse.userprofile

import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("x") val x: Double,
    @SerializedName("y") val y: Double
)
