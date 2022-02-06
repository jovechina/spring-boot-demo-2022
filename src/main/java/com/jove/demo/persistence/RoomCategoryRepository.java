package com.jove.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.RoomCategory;

@Repository
public interface RoomCategoryRepository extends CrudRepository<RoomCategory, Integer> {
	
	@Query(value="select c.key, c.key as categoryId, c.value as categoryName from codemaster c where c.category = 2", nativeQuery = true)
	public List<RoomCategory> fetchAll();
}
