package com.jove.demo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByUserName(String userName);

}
