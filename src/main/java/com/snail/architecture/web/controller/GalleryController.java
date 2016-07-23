package com.snail.architecture.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snail.architecture.entity.FilesBo;
import com.snail.architecture.service.FilesService;
import com.snail.architecture.util.PageUtils;

@RequestMapping(value = "/backend/gallery")
@Controller
public class GalleryController {
	@Autowired
	private FilesService filesService;

	@RequestMapping(method = RequestMethod.GET)
	public String init() {
		return "/backend/admin-gallery";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view")
	public @ResponseBody Object view(int page,int pagesize) {
		int startNum=page==1?0:(page-1)*pagesize;
		FilesBo vo = new FilesBo();
		vo.setStart(startNum);
		vo.setLimit(pagesize);
		List<FilesBo> files = filesService.findAll(vo);
		int totalCount = filesService.countFiles();
		return PageUtils.wrappQueryResult(files,totalCount);
	}
}
