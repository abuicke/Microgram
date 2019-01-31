package ie.gravitycode.core.util

import android.widget.ImageView
import com.bumptech.glide.RequestManager

class ImageLoaderImpl(private val glideRequestManager: RequestManager) : ImageLoader {

    override fun load(uri: String, imageView: ImageView) {
        glideRequestManager
            .load(uri)
            .into(imageView)
    }
}