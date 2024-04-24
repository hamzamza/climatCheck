package io.climatecheck.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.climatecheck.app.data.remote.api.CitiesApi
import io.climatecheck.app.data.repository.CityRepositoryImp
import io.climatecheck.app.domain.repository.CityRepository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CityModels {
    @Provides
    @Singleton
    fun provideApi(): CitiesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CitiesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: CitiesApi): CityRepository {
        return CityRepositoryImp(apiService)
    }
}
