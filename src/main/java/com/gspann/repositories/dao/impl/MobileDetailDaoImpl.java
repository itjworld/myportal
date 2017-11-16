package com.gspann.repositories.dao.impl;

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

}
