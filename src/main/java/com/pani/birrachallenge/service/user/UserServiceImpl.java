package com.pani.birrachallenge.service.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pani.birrachallenge.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	public static final String SALT = "birra";
	private UserRepository repo;
	private CacheManager cacheManager;

	@Autowired
	public UserServiceImpl(UserRepository repo, CacheManager cacheManager) {
		this.repo = repo;
		this.cacheManager = cacheManager;
	}

	@Override
	public String hashPassword(String password) {
		return generateHash(password);
	}

	@Override
	@Cacheable(value = "users", key = "#token")
	public User getUser(String token) {
		return cacheManager.getCache("users").get(token, User.class);
	}

	// We didn´t add any check, just to create some users to test
	@Override
	public void storeUserInDb(User user) {
		repo.save(user);
	}

	@Override
	public UserResponse login(String emailAddress, String password) {
		UserResponse response = new UserResponse();
		User user = null;
		try {
			user = repo.findByEmailAddress(emailAddress);
		} catch (Exception e) {
			logger.error("Error trying to fecth user from db", e);
			response.setMessage("error", "error trying to get user from db");
			return response;
		}
		if (user.getPassword().equals(this.generateHash(password))) {
			response.setUser(user);
			response.setMessage("ok", "user found in db");
			String uniqueToken = generateUniqueToken();
			storeUserInCache(user, uniqueToken);
			response.setMessage("uniqueToken", uniqueToken);
			return response;
		} else {
			response.setMessage("error", "password is not correct");
			return response;
		}
	}

	@CachePut(value = "users", key = "#token")
	public User storeUserInCache(User user, String token) {
		cacheManager.getCache("users").put(token, user);
		return user;
	}

	private String generateUniqueToken() {
		return UUID.randomUUID().toString();
	}

	private String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; idx++) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("Something went wrong during password hashing", e);
		}

		return hash.toString();
	}

	@Override
	@CacheEvict(value = "users", key = "#token")
	public void logout(String token) {
		// no logic needed
	}

}
