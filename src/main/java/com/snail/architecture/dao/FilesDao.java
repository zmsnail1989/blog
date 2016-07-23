package com.snail.architecture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.snail.architecture.entity.FilesBo;

public interface FilesDao {
	public int saveFiles(FilesBo file);
	public int deleteFiles(@Param("ids")List<String> ids);
	public int countFiles();
	public List<FilesBo> findAll(FilesBo file);
	public List<String> findRealPath(@Param("ids")List<String> ids,@Param("up_user")String up_user);
}
