package ie.gravitycode.microgram.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import ie.gravitycode.microgram.MvcViewFactory
import ie.gravitycode.microgram.MvcViewFactoryImpl
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides @Singleton fun providesContext() = context

    @Provides @Singleton fun providesLayoutInflater(): LayoutInflater = LayoutInflater.from(context)

    @Provides @Singleton fun providesMvcViewFactory(inflater: LayoutInflater): MvcViewFactory =
        MvcViewFactoryImpl(inflater)

}