package com.gspann.repositories.dao;

import java.util.List;

import com.gspann.entities.MobileDetail;

public interface MobileDetailDao {

	void save(MobileDetail desc);

	List<MobileDetail> getDetails();
	
	List<MobileDetail> getDetails(Integer page, Integer pageSize, Integer sort, String sortBy, String search);

	void delete(long id);

	void updateDesc(MobileDetail desc);

	long countRow(Class<MobileDetail> class1);

}
