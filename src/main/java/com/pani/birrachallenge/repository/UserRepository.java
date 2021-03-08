package com.pani.birrachallenge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pani.birrachallenge.service.user.User;

@Repository("userRepository")
public interface UserRepository extends MongoRepository<User, Integer> {

	@Query(value = "{'emailAddress' : {$regex : ?0}}")
	public User findByEmailAddress(String emailAddress);

}
