package com.pani.birrachallenge.web.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pani.birrachallenge.service.drink.DrinkService;
import com.pani.birrachallenge.service.drink.DrinkType;
import com.pani.birrachallenge.service.user.RestAdminAuth;

@RestController
@RequestMapping("/server/rest/drink")
public class DrinkEndpoint {

	private DrinkService drinkService;

	@Autowired
	public DrinkEndpoint(DrinkService drinkService) {
		this.drinkService = drinkService;
	}

	@GetMapping(path = { "/calculateBoxes" })
	@RestAdminAuth
	public Integer calculateHowManyBoxes(@RequestParam(name = "people", required = true) int people,
			@RequestParam(name = "location", required = true) String location,
			@RequestParam(name = "drinkType", required = true) DrinkType drinkType,
			@RequestParam(name = "meetupDate", required = false) @DateTimeFormat(pattern = "MM-dd-yyyy-HH-mm") Date meetupDate) {
		return drinkService.calculateHowManyBoxes(drinkType, people, location, meetupDate.getTime());
	}

}
