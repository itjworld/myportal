package com.gspann.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gspann.entities.MobileDetail;
import com.gspann.repositories.dao.MobileDetailDao;
import com.gspann.service.MobileDetailService;

@Service
public class MobileDetailServiceImpl implements MobileDetailService {

	@Autowired
	private MobileDetailDao mobileDetailDao;

	@Override
	@Transactional(readOnly = false)
	public void saveDesc(MobileDetail desc) {
		mobileDetailDao.save(desc);
	}

}
