package com.jove.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jove.demo.model.RoomType;
import com.jove.demo.persistence.RoomTypeRepository;

@Service
public class RoomTypeService {
	
	@Autowired
	RoomTypeRepository roomRep;
	
	public Iterable<RoomType> getAll() {
		return roomRep.findAll();
	}
	
	public RoomType getOne(int roomId) {
		return roomRep.findById(roomId).orElse(null);
	}
}
