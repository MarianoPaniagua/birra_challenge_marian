package com.pani.birrachallenge.service.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pani.birrachallenge.service.meetup.Meetup;

@Service
public class NotificationServiceImpl implements NotificationService {
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	// if i had an AWS account, I would create a request to AWS SQS service in order
	// to trigger a Lambda Function to send mails to all collaborators
	@Override
	public void notifyNewMeetup(Meetup meetup) {
		logger.info("Sending SQS message to tell people about: " + meetup.getName());

	}

}
