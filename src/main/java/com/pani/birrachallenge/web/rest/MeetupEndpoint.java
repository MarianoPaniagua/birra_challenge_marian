package com.pani.birrachallenge.web.rest;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pani.birrachallenge.service.meetup.Meetup;
import com.pani.birrachallenge.service.meetup.MeetupCreateRequest;
import com.pani.birrachallenge.service.meetup.MeetupResponse;
import com.pani.birrachallenge.service.meetup.MeetupService;
import com.pani.birrachallenge.service.user.RestAdminAuth;
import com.pani.birrachallenge.service.user.RestAuth;
import com.pani.birrachallenge.service.user.User;
import com.pani.birrachallenge.service.user.UserService;

@RestController
@RequestMapping("/server/rest/meetup")
public class MeetupEndpoint {

	MeetupService meetupService;
	UserService userService;

	public MeetupEndpoint(UserService userService, MeetupService meetupService) {
		this.userService = userService;
		this.meetupService = meetupService;
	}

	@GetMapping
	@RestAuth
	public List<Meetup> checkWeather(HttpServletRequest request) {
		return meetupService.getAllMeetups();

	}

	@PostMapping(path = { "/{meetupName}" })
	@RestAdminAuth
	public MeetupResponse createMeetup(HttpServletResponse response, HttpServletRequest request,
			@RequestBody MeetupCreateRequest meetupCreate,
			@PathVariable(name = "meetupDate", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy-HH-mm") Date meetupDate) {
		User user = userService.getUser(request.getHeader("unique-token"));
		meetupCreate.setTimestamp(meetupDate.getTime());
		meetupCreate.setCreator(user);
		MeetupResponse meetupResponse = meetupService.createMeetup(meetupCreate);
		if (meetupResponse.getCode().equals(201)) {
			response.setStatus(201);
		}
		return meetupResponse;

	}

	@PostMapping(path = { "/{meetupName}/join" })
	@RestAuth
	public MeetupResponse joinMeetup(HttpServletRequest request,
			@PathVariable(name = "meetupName", required = true) String meetupName) {
		User user = userService.getUser(request.getHeader("unique-token"));
		return meetupService.signUpIntoMeetup(meetupName, user);

	}

	@PostMapping(path = { "/{meetupName}/checkIn" })
	@RestAuth
	public MeetupResponse checkInMeetup(HttpServletRequest request,
			@PathVariable(name = "meetupName", required = true) String meetupName) {
		User user = userService.getUser(request.getHeader("unique-token"));
		return meetupService.checkedIn(meetupName, user);

	}

	@GetMapping(path = { "/{meetupName}/weather" })
	@RestAuth
	public MeetupResponse checkWeather(HttpServletRequest request,
			@PathVariable(name = "meetupName", required = true) String meetupName) {
		return meetupService.checkWeatherForMeetup(meetupName);

	}
}
