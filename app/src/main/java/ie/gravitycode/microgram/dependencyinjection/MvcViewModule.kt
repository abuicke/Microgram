package ie.gravitycode.microgram.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import ie.gravitycode.core.util.ToastManager
import ie.gravitycode.core.util.ToastManagerImpl
import ie.gravitycode.microgram.MvcViewFactory
import ie.gravitycode.microgram.MvcViewFactoryImpl
import javax.inject.Singleton

@Module
class MvcViewModule(private val context: Context) {

    @Provides @Singleton fun providesContext() = context

    @Provides @Singleton fun providesLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

    @Provides @Singleton fun providesToastManager(): ToastManager = ToastManagerImpl(context)

    @Provides @Singleton fun providesMvcViewFactory(
        inflater: LayoutInflater,
        toastManager: ToastManager
    ): MvcViewFactory =
        MvcViewFactoryImpl(inflater, toastManager)

}