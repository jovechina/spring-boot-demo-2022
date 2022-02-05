package com.jove.demo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.ContentResult;

@Repository
public interface ContentRepository extends CrudRepository<ContentResult, Integer> {
}
