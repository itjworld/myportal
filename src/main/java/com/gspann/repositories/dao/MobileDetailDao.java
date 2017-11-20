package com.gspann.repositories.dao;

import java.util.List;

import com.gspann.entities.MobileDetail;

public interface MobileDetailDao {

	void save(MobileDetail desc);

	List<MobileDetail> getDetails();

	void delete(long id);

	void updateDesc(MobileDetail desc);

}
