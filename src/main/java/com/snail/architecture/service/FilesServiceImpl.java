package com.snail.architecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snail.architecture.dao.FilesDao;
import com.snail.architecture.entity.FilesBo;

@Service
public class FilesServiceImpl implements FilesService {
	@Autowired
	private FilesDao fdao;

	@Override
	public int saveFiles(FilesBo file) {
		// TODO Auto-generated method stub
		return fdao.saveFiles(file);
	}

	@Override
	public int deleteFiles(List<String> ids) {
		// TODO Auto-generated method stub
		return fdao.deleteFiles(ids);
	}

	@Override
	public List<FilesBo> findAll(FilesBo file) {
		// TODO Auto-generated method stub
		return fdao.findAll(file);
	}

	@Override
	public int countFiles() {
		// TODO Auto-generated method stub
		return fdao.countFiles();
	}

	@Override
	public List<String> findRealPath(List<String> ids, String up_user) {
		// TODO Auto-generated method stub
		return fdao.findRealPath(ids, up_user);
	}

}
