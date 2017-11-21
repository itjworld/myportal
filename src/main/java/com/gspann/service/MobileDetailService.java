package com.gspann.service;

import java.util.List;

import com.gspann.beans.Message;
import com.gspann.entities.MobileDetail;

public interface MobileDetailService {

	void saveDesc(MobileDetail desc);

	List<MobileDetail> getDetails();

	void delete(long id);

	void updateDesc(MobileDetail desc);

	Message getDetails(Integer page, Integer pageSize, Integer sort, String sortBy, String searchParam);

}
