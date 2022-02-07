package com.jove.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jove.demo.model.Estimation;
import com.jove.demo.persistence.EstimationRepository;

@Service
public class EstimationService {
	
	@Autowired
	EstimationRepository estimationRep;
	
	public int insert(Estimation est) {
		est.setCreateDate(new Date());
		Estimation estvalue = estimationRep.save(est);
		
		return estvalue.getEstimationId();
	}
	
	public void update(Estimation est) {
		est.setUpdateDate(new Date());
		estimationRep.save(est);
	}

}
