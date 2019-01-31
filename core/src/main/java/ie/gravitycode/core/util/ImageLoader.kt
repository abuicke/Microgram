package ie.gravitycode.core.util

import android.widget.ImageView

interface ImageLoader {

    fun load(uri: String, imageView: ImageView)
}