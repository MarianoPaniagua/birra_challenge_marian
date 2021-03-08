package com.pani.birrachallenge.web.rest;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pani.birrachallenge.service.drink.DrinkService;
import com.pani.birrachallenge.service.drink.DrinkType;
import com.pani.birrachallenge.service.user.RestAdminAuth;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

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
	@ApiOperation(value = "Calculate How Many Boxes", notes = "Return how many boxes of the DrinkType you will need for the meetup")
	@ApiResponse(code = HttpServletResponse.SC_OK, message = "OK")
	public Integer calculateHowManyBoxes(
			@ApiParam(value = "People for the meetup", example = "12")  
			@RequestParam(name = "people", required = true) int people,
			@ApiParam(value = "Location for the meetup", example = "argentina") 
			@RequestParam(name = "location", required = true) String location,
			@ApiParam(value = "DrinkType to calcuate", example = "BIRRA") 
			@RequestParam(name = "drinkType", required = true) DrinkType drinkType,
			@ApiParam(value = "Date for the meetup", example = "03-10-2021-14-05")
			@RequestParam(name = "meetupDate", required = true) 
			@DateTimeFormat(pattern = "MM-dd-yyyy-HH-mm") Date meetupDate) {
		return drinkService.calculateHowManyBoxes(drinkType, people, location, meetupDate.getTime());
	}

}
