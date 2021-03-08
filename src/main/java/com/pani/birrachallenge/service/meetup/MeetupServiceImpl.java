package com.pani.birrachallenge.service.meetup;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pani.birrachallenge.repository.MeetupRepository;
import com.pani.birrachallenge.service.notification.NotificationService;
import com.pani.birrachallenge.service.user.User;
import com.pani.birrachallenge.service.weather.WeatherService;
import com.pani.birrachallenge.service.weather.openweather.Forecast;

@Service
public class MeetupServiceImpl implements MeetupService {

	private static final Logger logger = LoggerFactory.getLogger(MeetupServiceImpl.class);
	private MeetupRepository repo;
	private WeatherService weatherService;
	private NotificationService notificationSevice;

	@Autowired
	public MeetupServiceImpl(MeetupRepository repo, WeatherService weatherService,
			NotificationService notificationSevice) {
		this.repo = repo;
		this.weatherService = weatherService;
		this.notificationSevice = notificationSevice;
	}

	@Override
	@CacheEvict(value = "meetups")
	public MeetupResponse createMeetup(MeetupCreateRequest meetupCreate) {
		MeetupResponse response = new MeetupResponse();
		Meetup meetup = new Meetup();
		try {
			if (checkIfAlreadyExistWithThatName(meetupCreate)) {
				response.setMessage("error", "There was another meetup already with that name");
				response.setCode(200);
				return response;
			}
			meetup.setCreator(meetupCreate.getCreator());
			meetup.setDate(meetupCreate.getTimestamp());
			meetup.setName(meetupCreate.getName());
			meetup.setLocation(meetupCreate.getLocation());
			meetupCreate.getDrinks().entrySet()
					.forEach(entry -> meetup.setDrinkForTheParty(entry.getKey(), entry.getValue()));

			repo.save(meetup);
			notificationSevice.notifyNewMeetup(meetup);
			response.setCode(201);
		} catch (Exception e) {
			logger.error("There was an error trying to create meetup", e);
			response.setMessage("error", "There was an error trying to create meetup");
			response.setCode(500);
			return response;
		}

		response.setMeetup(meetup);
		response.setMessage("ok", "meetup added to db");
		return response;
	}

	private boolean checkIfAlreadyExistWithThatName(MeetupCreateRequest meetupCreate) {
		Meetup check = repo.findByName(meetupCreate.getName());
		if (check != null) {
			return true;
		}
		return false;
	}

	@Override
	public MeetupResponse signUpIntoMeetup(String name, User user) {
		MeetupResponse response = new MeetupResponse();
		Meetup meetup;
		try {
			meetup = repo.findByName(name);
			meetup.addGuest(user);
			repo.save(meetup);
		} catch (Exception e) {
			logger.error("There was an error trying signup into the meetup:" + name, e);
			response.setMessage("error", "There was an error trying to signup into the meetup:" + name);
			return response;
		}
		response.setMeetup(meetup);
		response.setMessage("ok", "User " + user.getId() + " added to meetup: " + meetup.getName());
		return response;
	}

	@Override
	public MeetupResponse checkedIn(String name, User user) {
		MeetupResponse response = new MeetupResponse();
		Meetup meetup;
		try {
			meetup = repo.findByName(name);
			meetup.addCheckedIn(user);
			repo.save(meetup);
		} catch (Exception e) {
			logger.error("There was an error trying checkIn into the meetup: " + name, e);
			response.setMessage("error", "There was an error trying to checkIn into the meetup:" + name);
			return response;
		}
		response.setMeetup(meetup);
		response.setMessage("ok", "User " + user.getId() + " checkedIn to meetup: " + meetup.getName());
		return response;
	}

	@Override
	public MeetupResponse checkWeatherForMeetup(String meetupName) {
		MeetupResponse response = new MeetupResponse();
		try {
			Meetup meetup = repo.findByName(meetupName);
			Forecast forecastThatDay = weatherService.getForecastByLocationAndDate(meetup.getLocation(),
					meetup.getDate());

			response.setMessage("temperature", String.valueOf(forecastThatDay.getMain().getTemp()));
			response.setMeetup(meetup);
			return response;
		} catch (Exception e) {
			logger.error("There was an error trying to fecth data for meetup: " + meetupName, e);
			response.setMessage("error", "There was an error trying to fecth data for meetup: " + meetupName);
			return response;
		}

	}

	@Override
	@Cacheable(value = "meetups")
	public List<Meetup> getAllMeetups() {
		return repo.findAll();
	}

}
