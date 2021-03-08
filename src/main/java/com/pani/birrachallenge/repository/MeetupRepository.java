package com.pani.birrachallenge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.pani.birrachallenge.service.meetup.Meetup;

@Repository("meetupRepository")
public interface MeetupRepository extends MongoRepository<Meetup, Integer> {

	@Query(value = "{'name' : {$regex : ?0}}")
	public Meetup findByName(String name);

}
