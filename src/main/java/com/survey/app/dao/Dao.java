package com.survey.app.dao;

import java.util.List;

import com.survey.app.entity.Entity;



public interface Dao<T extends Entity, I>
{

	List<T> findAll();


	T find(I id);


	T save(T newsEntry);

	T update(T newsEntry);
	
	void delete(I id);


	

}