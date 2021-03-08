package com.pani.birrachallenge.service.meetup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pani.birrachallenge.service.drink.DrinkType;
import com.pani.birrachallenge.service.user.User;

@Document(collection = "meetups")
public class Meetup implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	private ObjectId id;
	private String name;
	private List<User> listOfGuests;
	private List<User> listOfCheckedIn;
	private Map<DrinkType, Integer> drinksForTheParty;
	private User creator;
	private Long date;
	private String Location;

	public Meetup() {
		this.listOfCheckedIn = new ArrayList<User>();
		this.listOfGuests = new ArrayList<User>();
		this.drinksForTheParty = new HashMap<DrinkType, Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getListOfGuests() {
		return listOfGuests;
	}

	public void setListOfGuests(List<User> listOfGuests) {
		this.listOfGuests = listOfGuests;
	}

	public List<User> getListOfCheckedIn() {
		return listOfCheckedIn;
	}

	public void setListOfCheckedIn(List<User> listOfCheckedIn) {
		this.listOfCheckedIn = listOfCheckedIn;
	}

	public Map<DrinkType, Integer> getDrinksForTheParty() {
		return drinksForTheParty;
	}

	public void setDrinkForTheParty(DrinkType drink, Integer quantity) {
		this.drinksForTheParty.put(drink, quantity);
	}

	public ObjectId getId() {
		return id;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public void addGuest(User user) {
		this.listOfGuests.add(user);
	}

	public void addCheckedIn(User user) {
		this.listOfCheckedIn.add(user);
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

}
