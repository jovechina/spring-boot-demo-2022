package com.jove.demo.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jove.demo.model.CodeMaster;

@Repository
public interface CodeMasterRepository extends CrudRepository<CodeMaster, Integer> {

	public List<CodeMaster> findByCategory(int category);
}
