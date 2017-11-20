package com.gspann.repositories.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gspann.entities.MobileDetail;
import com.gspann.repositories.dao.MobileDetailDao;
import com.gspann.repositories.utils.GenericAbstractDao;

@Repository
public class MobileDetailDaoImpl extends GenericAbstractDao<MobileDetail> implements MobileDetailDao{

	@Override
	public void save(MobileDetail desc) {
		create(desc);
	}

	@Override
	public List<MobileDetail> getDetails() {
		return fetchAll(MobileDetail.class);
	}

	@Override
	public void delete(long id) {
		delete(id, MobileDetail.class);
		
	}

	@Override
	public void updateDesc(MobileDetail desc) {
		update(desc);
	}

}
