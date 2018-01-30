package cn.itcast.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.itcast.common.dao.BaseDao;
import cn.itcast.common.service.BaseService;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	
	private BaseDao<T, ID> dao;
	public void setDao(BaseDao<T, ID> dao) {
		this.dao = dao;
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<T> findAll(Specification<T> spec) {
		return dao.findAll(spec);
	}
	@Override
	public Page<T> findAll(Specification<T> spec,Pageable pageable) {
		return dao.findAll(spec, pageable);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public T findOne(ID id) {
		return dao.findOne(id);
	}

	@Override
	public void saveOrUpdate(T dept) {
		dao.save(dept);
	}

	@Override
	public void delete(ID id) {
		dao.delete(id);
	}

}
