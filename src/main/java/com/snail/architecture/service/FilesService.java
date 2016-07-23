package com.snail.architecture.service;

import java.util.List;

import com.snail.architecture.entity.FilesBo;

public interface FilesService {
	public int saveFiles(FilesBo file);
	public int deleteFiles(List<String> ids);
	public List<FilesBo> findAll(FilesBo file);
	public int countFiles();
	public List<String> findRealPath(List<String> ids,String up_user);
}
