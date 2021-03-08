package com.pani.birrachallenge.service.user;

import java.util.HashMap;
import java.util.Map;

public class UserResponse {

	User user;
	Map<String, String> messages;

	public UserResponse() {
		this.messages = new HashMap<String, String>();
	}

	public UserResponse(String defaultValue) {
		this.messages = new HashMap<String, String>();
		messages.put("default", defaultValue);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessage(String messageKey, String nessageValue) {
		this.messages.put(messageKey, nessageValue);
	}

}
