package com.jove.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jove.demo.persistence.RoomCategoryRepository;
import com.jove.demo.model.RoomCategory;

@Service
public class RoomCategoryService {

	@Autowired
	RoomCategoryRepository roomCategoryRep;
	
	public Iterable<RoomCategory> selectAll() {
		return roomCategoryRep.fetchAll();
	}
}
