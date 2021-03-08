package com.pani.birrachallenge.service.meetup;

import java.util.HashMap;
import java.util.Map;

import com.pani.birrachallenge.service.drink.DrinkType;
import com.pani.birrachallenge.service.user.User;

public class MeetupCreateRequest {

	private Map<DrinkType, Integer> drinks;
	private Long timestamp;
	private User creator;
	private String name;
	private String Location;

	public MeetupCreateRequest() {
		this.drinks = new HashMap<DrinkType, Integer>();
	}

	public Map<DrinkType, Integer> getDrinks() {
		return drinks;
	}

	public void setDrink(DrinkType drink, Integer quantity) {
		this.drinks.put(drink, quantity);
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

}
