package com.pani.birrachallenge.service.drink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.pani.birrachallenge.service.weather.WeatherService;
import com.pani.birrachallenge.service.weather.WeatherType;

@Service
public class DrinkServiceImpl implements DrinkService {

	private Environment env;
	private WeatherService weatherService;

	@Autowired
	public DrinkServiceImpl(Environment env, WeatherService weatherService) {
		this.env = env;
		this.weatherService = weatherService;
	}

	@Override
	public int calculateHowManyBoxes(DrinkType drinkType, int people, String location, Long dateForMeetup) {

		WeatherType weatherType = weatherService.getWeatherType(location, dateForMeetup);
		//mas visible, el 6 constante
		return (int) Math
				.ceil((Double.valueOf(env.getProperty(drinkType.name() + "." + weatherType.name())) * people) / 6);
	}

}
