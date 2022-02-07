package com.jove.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jove.demo.model.EstimationShortList;

public interface EstimationShortListRepository extends CrudRepository<EstimationShortList, Integer> {
	@Query(value = "Select estimationid,b.username,c.roomType,d.value as interiordecoration," + "wires,cable, createdate, updatedate from estimation a, user b, roomtype c, "
			+ "codemaster d where a.userid = b.userid and a.roomid = c.roomid and " + "a.interiordecoration = d.key and d.category = 1", nativeQuery = true)
	public List<EstimationShortList> selectToSearchResult();
}
