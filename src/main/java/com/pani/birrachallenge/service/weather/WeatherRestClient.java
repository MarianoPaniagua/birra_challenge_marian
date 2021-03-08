package com.pani.birrachallenge.service.weather;

import java.util.List;

import com.pani.birrachallenge.service.weather.openweather.Forecast;

public interface WeatherRestClient {

	//The free api was only for 5 days forecast
	List<Forecast> getForecast5Days(String location);

}
