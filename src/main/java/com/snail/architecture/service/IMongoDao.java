package com.snail.architecture.service;

import com.mongodb.gridfs.GridFSDBFile;


public interface IMongoDao{
	public boolean saveFile(byte[] data, String collectionName, String fileName);
	public GridFSDBFile retrieveFileOne(String collectionName, String filename);
}
