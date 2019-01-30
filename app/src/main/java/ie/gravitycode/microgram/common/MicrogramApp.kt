package ie.gravitycode.microgram.common

import android.app.Application
import ie.gravitycode.microgram.dependencyinjection.AppComponent
import ie.gravitycode.microgram.dependencyinjection.AppModule
import ie.gravitycode.microgram.dependencyinjection.DaggerAppComponent

class MicrogramApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent() = appComponent

}