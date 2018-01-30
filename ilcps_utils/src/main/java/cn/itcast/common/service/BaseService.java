package cn.itcast.common.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface BaseService<T, ID extends Serializable> {

	/**
	 * 查询全部
	 */
	List<T> findAll();
	
	/**
	 * 查询全部
	 */
	List<T> findAll(Specification<T> spec);

	/**
	 * 条件分页查询
	 */
	Page<T> findAll(Specification<T> spec, Pageable pageable);
	
	/**
	 * 分页查询全部
	 * 
	 * @return
	 */
	Page<T> findAll(Pageable pageable);

	/**
	 * 主键查询
	 */
	T findOne(ID id);

	/**
	 * 保存或更新
	 */
	void saveOrUpdate(T dept);

	/**
	 * 删除方法
	 */
	void delete(ID id);

	
}
