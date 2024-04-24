package io.climatecheck.app.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.climatecheck.app.domain.repository.CityRepository
import io.climatecheck.app.domain.repository.WeatherRepository
import io.climatecheck.app.domain.schema.CitySchema
import io.climatecheck.app.domain.schema.WeatherShema
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel@Inject constructor(private val weatherRepository: WeatherRepository ,
   private val citiesRepository:  CityRepository   )
    : ViewModel(){

    private val _weatherData = MutableStateFlow<WeatherShema?>(  null )
    val weatherData: StateFlow<WeatherShema? > = _weatherData

    private val _location  = MutableStateFlow(  Pair(33.44 , -94.04) )
    val location : StateFlow<Pair<Double  , Double >> = _location

    private val _city   = MutableStateFlow<CitySchema>(  CitySchema("Casablanca " , "MA" , 33.520410 , -7.635320) )
    val city : StateFlow<CitySchema   > = _city

    private val _citiesList   = MutableStateFlow<List<CitySchema>>(  emptyList() )
    val citiesList : StateFlow<List<CitySchema> > = _citiesList

    private val _isScoopForSearch = MutableStateFlow(false)
    val  isScoopForSearch : StateFlow<Boolean> = _isScoopForSearch

    private val _loadingStateFlow = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loadingStateFlow

    private val _errorStateFlow = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _errorStateFlow


    fun scoopIn(isScoop : Boolean){
        viewModelScope.launch {
            _isScoopForSearch.emit(isScoop)
        }
    }

    private fun fetchData(  latitude: Double , longitude: Double ) {
        viewModelScope.launch {
        _loadingStateFlow.emit(true)
            try {
                val data = weatherRepository.getWeatherData(latitude,longitude)
                _weatherData.emit(data)
            }catch (e : Exception){
                Log.e("somewhere", "fetchData: $e", )
            }
        _loadingStateFlow.emit(false)
        }
    }
      @SuppressLint("SuspiciousIndentation")
      fun searchforCity(name : String ){
        viewModelScope.launch {
            if(name.isNotEmpty())
                _citiesList.emit(citiesRepository.fetchCitiesByName(name))
            else
                _citiesList.emit(emptyList())
            }
        }
        fun selectCity(citySchema: CitySchema){
               viewModelScope
                   .launch {  _city.emit(citySchema)
                       fetchData(citySchema.latitude , citySchema.longitude)
                       _citiesList.emit(emptyList())
                       _isScoopForSearch.emit(false)}
        }


    init {
        fetchData(   location.value.first , location.value.second   )
       }

}