package com.jove.demo.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.CodeMaster;
import com.jove.demo.model.RoomCategory;

@Repository
public interface CodeMasterRepository extends CrudRepository<CodeMaster, Integer> {

	public List<CodeMaster> findByCategory(int category);

//	public List<RoomCategory> findAllRoomCategories(int category);
}
