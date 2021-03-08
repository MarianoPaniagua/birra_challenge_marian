package com.pani.birrachallenge.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pani.birrachallenge.service.user.RestAuth;
import com.pani.birrachallenge.service.user.UserResponse;
import com.pani.birrachallenge.service.user.UserService;

@RestController
@RequestMapping("/server/rest/user")
public class UserEndpoint {

	UserService userService;

	@Autowired
	public UserEndpoint(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(path = { "/login" })
	public UserResponse login(HttpServletRequest request,
			@RequestParam(value = "emailAddress", required = true) String emailAddress,
			@RequestParam(value = "password", required = true) String password) {
		return userService.login(emailAddress, password);
	}

	@GetMapping(path = { "/logout" })
	@RestAuth
	public void logout(HttpServletRequest request) {
		userService.logout(request.getHeader("unique-token"));
	}

}
