package com.jove.demo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.Estimation;

@Repository
public interface EstimationRepository extends CrudRepository<Estimation, Integer> {

}
