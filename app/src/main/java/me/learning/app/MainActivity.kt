package me.learning.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import me.learning.weathernotfound.domain.currentWeather.presentationModels.CurrentWeatherModel
import me.learning.weathernotfound.domain.fiveDayThreeHourForecast.presentationModels.FiveDayThreeHourForecastModel
import me.learning.weathernotfound.presentation.WeatherNotFound
import me.learning.weathernotfound.presentation.WeatherNotFoundCallback
import me.learning.weathernotfound.presentation.WeatherNotFoundError
import me.learning.weathernotfound.presentation.WeatherNotFoundResponse

class MainActivity : AppCompatActivity() {

    private lateinit var currentWeatherMaterialButton: MaterialButton
    private lateinit var fiveDayThreeHourMaterialButton: MaterialButton
    private lateinit var currentWeatherWithNameMaterialButton: MaterialButton
    private lateinit var fiveDayThreeHourWithNameMaterialButton: MaterialButton
    private lateinit var invalidateFiveDayThreeHourMaterialButton: MaterialButton
    private lateinit var invalidateCurrentWeatherMaterialButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        currentWeatherMaterialButton.setOnClickListener { startCurrentWeatherRequest() }

        fiveDayThreeHourMaterialButton.setOnClickListener { startFiveDayThreeHourRequest() }

        currentWeatherWithNameMaterialButton.setOnClickListener { startCurrentWeatherRequest("US") }

        fiveDayThreeHourWithNameMaterialButton.setOnClickListener { startFiveDayThreeHourRequest("US") }

        invalidateFiveDayThreeHourMaterialButton.setOnClickListener {
            WeatherNotFound.getInstance().invalidateFiveDayThreeHourForecastCache()
        }

        invalidateCurrentWeatherMaterialButton.setOnClickListener {
            WeatherNotFound.getInstance().invalidateCurrentWeatherCache()
        }
    }

    private fun initViews() {
        currentWeatherMaterialButton =
            findViewById(R.id.currentWeatherWithCoordinatesMaterialButton)
        fiveDayThreeHourMaterialButton =
            findViewById(R.id.fiveDayThreeHourWithCoordinatesMaterialButton)
        currentWeatherWithNameMaterialButton =
            findViewById(R.id.currentWeatherWithNameMaterialButton)
        fiveDayThreeHourWithNameMaterialButton =
            findViewById(R.id.fiveDayThreeHourWithNameMaterialButton)
        invalidateFiveDayThreeHourMaterialButton =
            findViewById(R.id.invalidateFiveDayThreeHourMaterialButton)
        invalidateCurrentWeatherMaterialButton =
            findViewById(R.id.invalidateCurrentWeatherMaterialButton)
    }

    private fun startCurrentWeatherRequest(latitude: Double = 0.0, longitude: Double = 0.0) {
        WeatherNotFound.getInstance().getCurrentWeatherInformation(
            latitude = latitude,
            longitude = longitude,
            weatherNotFoundCallback = object :
                WeatherNotFoundCallback<WeatherNotFoundResponse<CurrentWeatherModel>, WeatherNotFoundError> {
                override fun onSuccess(response: WeatherNotFoundResponse<CurrentWeatherModel>) {
                    // Do whatever you want...
                    Toast.makeText(this@MainActivity, "$response", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: WeatherNotFoundError) {
                    error.exception?.printStackTrace()
                }
            }
        )
    }

    private fun startCurrentWeatherRequest(cityName: String) {
        WeatherNotFound.getInstance().getCurrentWeatherInformation(
            cityName = cityName,
            weatherNotFoundCallback = object :
                WeatherNotFoundCallback<WeatherNotFoundResponse<CurrentWeatherModel>, WeatherNotFoundError> {
                override fun onSuccess(response: WeatherNotFoundResponse<CurrentWeatherModel>) {
                    // Do whatever you want...
                    Toast.makeText(this@MainActivity, "$response", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: WeatherNotFoundError) {
                    error.exception?.printStackTrace()
                }
            }
        )
    }

    private fun startFiveDayThreeHourRequest(latitude: Double = 0.0, longitude: Double = 0.0) {
        WeatherNotFound.getInstance().getFiveDayThreeHourForecastInformation(
            latitude = latitude,
            longitude = longitude,
            weatherNotFoundCallback = object :
                WeatherNotFoundCallback<WeatherNotFoundResponse<FiveDayThreeHourForecastModel>, WeatherNotFoundError> {
                override fun onSuccess(response: WeatherNotFoundResponse<FiveDayThreeHourForecastModel>) {
                    // Do whatever you want...
                    Toast.makeText(this@MainActivity, "$response", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: WeatherNotFoundError) {
                    error.exception?.printStackTrace()
                }
            }
        )
    }

    private fun startFiveDayThreeHourRequest(cityName: String) {
        WeatherNotFound.getInstance().getFiveDayThreeHourForecastInformation(
            cityName = cityName,
            weatherNotFoundCallback = object :
                WeatherNotFoundCallback<WeatherNotFoundResponse<FiveDayThreeHourForecastModel>, WeatherNotFoundError> {
                override fun onSuccess(response: WeatherNotFoundResponse<FiveDayThreeHourForecastModel>) {
                    // Do whatever you want...
                    Toast.makeText(this@MainActivity, "$response", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: WeatherNotFoundError) {
                    error.exception?.printStackTrace()
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        WeatherNotFound.getInstance().destroy()
    }

}