package io.climatecheck.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.climatecheck.app.data.remote.api.WeatherApi
import io.climatecheck.app.data.repository.WeatherRepositoryImpl
import io.climatecheck.app.domain.repository.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object WeatherModels {
    @Provides
    @Singleton
    fun provideApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/3.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }
}

