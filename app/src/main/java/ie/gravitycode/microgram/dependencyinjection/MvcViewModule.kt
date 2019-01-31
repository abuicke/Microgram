package ie.gravitycode.microgram.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import ie.gravitycode.core.util.ImageLoader
import ie.gravitycode.core.util.ImageLoaderImpl
import ie.gravitycode.core.util.ToastManager
import ie.gravitycode.core.util.ToastManagerImpl
import ie.gravitycode.microgram.mvc.MvcViewFactory
import ie.gravitycode.microgram.mvc.MvcViewFactoryImpl
import javax.inject.Singleton

@Module
class MvcViewModule(private val context: Context) {

    @Provides @Singleton fun providesContext() = context

    @Provides @Singleton fun providesLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

    @Provides @Singleton fun providesGlide(): RequestManager = Glide.with(context)

    @Provides @Singleton fun providesImageLoader(glide: RequestManager): ImageLoader =
        ImageLoaderImpl(glide)

    @Provides @Singleton fun providesToastManager(): ToastManager = ToastManagerImpl(context)

    @Provides @Singleton fun providesMvcViewFactory(
        inflater: LayoutInflater,
        imageLoader: ImageLoader,
        toastManager: ToastManager
    ): MvcViewFactory =
        MvcViewFactoryImpl(inflater, imageLoader, toastManager)

}