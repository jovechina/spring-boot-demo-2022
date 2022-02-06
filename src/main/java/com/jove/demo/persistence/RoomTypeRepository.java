package com.jove.demo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.RoomType;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Integer>  {

}
