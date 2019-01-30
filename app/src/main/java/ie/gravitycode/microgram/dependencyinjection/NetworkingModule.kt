package ie.gravitycode.microgram.dependencyinjection

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ie.gravitycode.instagram.api.InstagramApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Provides @Singleton fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.instagram.com/v1/users/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    @Provides @Singleton fun providesInstagramApi(retrofit: Retrofit): InstagramApi =
        retrofit.create(InstagramApi::class.java)
}