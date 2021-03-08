package com.pani.birrachallenge.service.notification;

import com.pani.birrachallenge.service.meetup.Meetup;

public interface NotificationService {

	void notifyNewMeetup(Meetup meetup);
}
