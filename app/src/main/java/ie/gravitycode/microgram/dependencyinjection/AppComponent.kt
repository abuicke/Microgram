package ie.gravitycode.microgram.dependencyinjection

import dagger.Component
import ie.gravitycode.microgram.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

}