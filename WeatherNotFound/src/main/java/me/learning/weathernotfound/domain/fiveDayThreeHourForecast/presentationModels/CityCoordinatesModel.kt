package me.learning.weathernotfound.domain.fiveDayThreeHourForecast.presentationModels

/**
* Representing city coordinates of given coordinates
* @property latitude The x value
* @property longitude The y value
*/
data class CityCoordinatesModel(
    val latitude: Double,
    val longitude: Double
)