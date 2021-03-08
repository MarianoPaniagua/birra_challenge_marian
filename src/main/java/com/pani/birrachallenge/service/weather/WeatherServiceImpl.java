package com.pani.birrachallenge.service.weather;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pani.birrachallenge.service.weather.openweather.Forecast;

@Service
public class WeatherServiceImpl implements WeatherService {

	private WeatherRestClient restClient;

	@Value("${high.temp}")
	private String highTemp;

	@Value("${high.temp}")
	private String lowTemp;

	@Autowired
	public WeatherServiceImpl(WeatherRestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public WeatherType getWeatherType(String location, Long dateForMeetup) {
		Forecast weatherResponse = getForecastByLocationAndDate(location, dateForMeetup);
		return weatherResponse == null ? WeatherType.NORMAL : calculateWeatherType(weatherResponse);
	}

	private WeatherType calculateWeatherType(Forecast data) {
		Double temp = data.getMain().getTemp();
		if (temp > Double.valueOf(highTemp)) {
			return WeatherType.HOT;
		}
		if (temp < Double.valueOf(lowTemp)) {
			return WeatherType.COLD;
		}
		return WeatherType.NORMAL;
	}

	@Override
	//cachear esto
	public Forecast getForecastByLocationAndDate(String location, Long meetUpDate) {
		List<Forecast> listOfForecast = restClient.getForecast5Days(location);
		List<Long> listOfDates = listOfForecast.stream().map(Forecast::getDt).collect(Collectors.toList());
		Long nearestDate = listOfDates.stream().min(Comparator.comparingLong(i -> Math.abs(i - meetUpDate / 1000)))
				.orElse(System.currentTimeMillis());
		Forecast forecastThatDay = listOfForecast.stream().filter(fore -> fore.getDt().equals(nearestDate)).findFirst()
				.get();
		return forecastThatDay;
	}
}
