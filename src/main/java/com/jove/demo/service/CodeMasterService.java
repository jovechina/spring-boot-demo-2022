package com.jove.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jove.demo.model.CodeMaster;
import com.jove.demo.persistence.CodeMasterRepository;

@Service
public class CodeMasterService {
	
	@Autowired
	CodeMasterRepository codeMasterRep;
	
	public List<CodeMaster> getByCategory(int category) {
		List<CodeMaster> codeMasters = codeMasterRep.findByCategory(category);
		return codeMasters;
	}
		
}
