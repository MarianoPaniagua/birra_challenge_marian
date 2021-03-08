package com.pani.birrachallenge.service.drink;

public interface DrinkService {

	int calculateHowManyBoxes(DrinkType drinkType, int people, String location, Long dateForMeetup);

}
