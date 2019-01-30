package ie.gravitycode.core.util

import android.content.Context
import android.widget.Toast

class ToastManagerImpl(private val context: Context) : ToastManager {

    override fun toastShort(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    override fun toastLong(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}