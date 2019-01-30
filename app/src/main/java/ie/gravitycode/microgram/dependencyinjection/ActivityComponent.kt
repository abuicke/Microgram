package ie.gravitycode.microgram.dependencyinjection

import dagger.Component
import ie.gravitycode.microgram.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [MvcViewModule::class, NetworkingModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}