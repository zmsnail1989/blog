package com.snail.architecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snail.architecture.dao.SequenceDao;

@Service
public class SequenceServiceImpl implements SequenceService{

	@Autowired
	private SequenceDao squDao;
	
	@Override
	public Long getCurrvalSequence(String tableName) {
		// TODO Auto-generated method stub
		return squDao.getCurrvalSequence(tableName);
	}

	@Override
	public Long getNextvalSequence(String tableName) {
		// TODO Auto-generated method stub
		return squDao.getNextvalSequence(tableName);
	}

}
