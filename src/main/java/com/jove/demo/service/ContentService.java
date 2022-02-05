package com.jove.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jove.demo.model.ContentResult;
import com.jove.demo.persistence.ContentRepository;

@Service
public class ContentService {
	@Autowired
	ContentRepository contentRep;

	public ContentResult selectContentById(int id) {
		return  contentRep.findById(id).orElse(null);
	}
}
