package com.pani.birrachallenge.service.meetup;

import java.util.HashMap;
import java.util.Map;

public class MeetupResponse {

	private Meetup meetup;
	Map<String, String> messages;
	Integer code;

	public MeetupResponse() {
		this.messages = new HashMap<String, String>();
	}

	public Meetup getMeetup() {
		return meetup;
	}

	public void setMeetup(Meetup meetup) {
		this.meetup = meetup;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessage(String messageKey, String nessageValue) {
		this.messages.put(messageKey, nessageValue);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
