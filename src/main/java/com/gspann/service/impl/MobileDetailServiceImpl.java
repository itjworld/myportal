package com.gspann.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gspann.beans.Message;
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

	@Override
	@Transactional(readOnly = true)
	public List<MobileDetail> getDetails() {
		return mobileDetailDao.getDetails();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) {
		mobileDetailDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateDesc(MobileDetail desc) {
		mobileDetailDao.updateDesc(desc);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Message getDetails(Integer page, Integer pageSize, Integer sort, String sortBy, String searchParam) {
		Message result= new Message();
		result.setId(mobileDetailDao.countRow(MobileDetail.class));
		result.setResults(mobileDetailDao.getDetails(page, pageSize, sort, sortBy, searchParam));
		return result;
	}

}
