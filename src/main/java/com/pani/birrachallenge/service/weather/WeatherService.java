package com.pani.birrachallenge.service.weather;

import com.pani.birrachallenge.service.weather.openweather.Forecast;

public interface WeatherService {

	WeatherType getWeatherType(String location, Long dateForMeetup);

	Forecast getForecastByLocationAndDate(String location, Long meetupDate);

}
