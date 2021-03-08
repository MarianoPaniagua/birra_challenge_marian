package com.pani.birrachallenge.service.user;

public interface UserService {

	String hashPassword(String password);

	User getUser(String token);

	void storeUserInDb(User user);
	
	UserResponse login(String emailAddress, String password);
	
	void logout(String token);

}
