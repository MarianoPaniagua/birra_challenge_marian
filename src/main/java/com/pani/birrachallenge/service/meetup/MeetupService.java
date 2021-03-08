package com.pani.birrachallenge.service.meetup;

import java.util.List;

import com.pani.birrachallenge.service.user.User;

public interface MeetupService {
	
	MeetupResponse createMeetup(MeetupCreateRequest meetupCreate);
	
	MeetupResponse signUpIntoMeetup(String name, User user);
	
	MeetupResponse checkedIn(String name, User user);

	MeetupResponse checkWeatherForMeetup(String meetupName);

	List<Meetup> getAllMeetups();


}
