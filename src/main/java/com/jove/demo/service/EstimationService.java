package com.jove.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jove.demo.model.Estimation;
import com.jove.demo.model.EstimationShortList;
import com.jove.demo.persistence.EstimationRepository;
import com.jove.demo.persistence.EstimationShortListRepository;

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
	
	public Estimation selectOne(int id) {
		return estimationRep.findById(id).orElse(null);
	}
	
	public void del(int id) {
		estimationRep.deleteById(id);
	}
	@Autowired
	EstimationShortListRepository estimationShortListRep;
	
	public List<EstimationShortList> selectToSearchResult() {
		return estimationShortListRep.selectToSearchResult();
	}

}
