package com.gspann.repositories.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

	@Override
	public List<MobileDetail> getDetails(Integer page, Integer pageSize, Integer sort, String sortBy, String search) {
		CriteriaQuery<MobileDetail> criteria = null;
		CriteriaBuilder criteriaBuilder = null;
		List<MobileDetail> results=null;
		Root<MobileDetail> from=null;
		Order order=null;
		String column="id";
		try {
			Session session = getCurrentSession();
			criteriaBuilder = session.getCriteriaBuilder();
			criteria = criteriaBuilder.createQuery(MobileDetail.class);
			from = criteria.from(MobileDetail.class);
			switch (sort) {
			case 0:
				column="name";
				break;
			case 1:
				column="mobile";
				break;
			case 2:
				column="description";
			default:
				column="id";
				break;
			}
			if("DESC".equalsIgnoreCase(sortBy)) {
				order=criteriaBuilder.desc(from.get(column));
			}else {
				order=criteriaBuilder.asc(from.get(column));
			}
			criteria.select(from).orderBy(order);
			if(!StringUtils.isEmpty(search))
				criteria.where(criteriaBuilder.like(from.get("name"),  "%" + search + "%"));
			results= session.createQuery(criteria).setFirstResult(page).setMaxResults(pageSize).getResultList();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			criteria = null;
			criteriaBuilder = null;
			from=null;
			order=null;
			
		}
		return results;
	}
	

}
