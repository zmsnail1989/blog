package com.snail.architecture.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.snail.architecture.entity.FilesBo;
import com.snail.architecture.entity.User;
import com.snail.architecture.service.FilesService;
import com.snail.architecture.util.Pair;
import com.snail.architecture.web.bind.annotation.CurrentUser;

/**
 * 图片上传管理
 * 
 * @author zhangmin
 *
 */
@RequestMapping(value = "/backend/picmanage")
@Controller
public class PicManagerController {
	@Autowired
	ServletContext context;
	@Autowired
	private FilesService fileservice;

	@RequestMapping(method = RequestMethod.GET)
	public String init() {
		return "/backend/admin-picture";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	public @ResponseBody Object findAll(@CurrentUser User loginUser) {
		FilesBo bo = new FilesBo();
		bo.setUp_user(loginUser.getUsername());
		List<FilesBo> all = fileservice.findAll(bo);
		Map<String,Object> rtnMap = new HashMap<>();
		rtnMap.put("recordsFiltered", all.size());
		rtnMap.put("recordsTotal", all.size());
		rtnMap.put("draw", 0);
		rtnMap.put("data", all);
		return rtnMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/upload")
	public String picUpload() {
		return "/backend/admin-picUpload";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public @ResponseBody Object delete(@RequestParam(value="ids") String ids,@CurrentUser User loginUser){
		String up_user = loginUser.getUsername();
		String[] idlist =ids.split(",");
		List<String> paths = fileservice.findRealPath(Arrays.asList(idlist), up_user);
		for (String path : paths) {
			File f = new File(path); // 输入要删除的文件位置
			if(f.exists()){
				f.delete();
			}
		}
		fileservice.deleteFiles(Arrays.asList(idlist));
		return "ok";
	}

	@RequestMapping("/uploadFile")
	public @ResponseBody Object saveFile(MultipartHttpServletRequest request, @CurrentUser User loginUser) {
		Map<String, String> map = new HashMap<String, String>();
		List<MultipartFile> files = request.getFiles("file");
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = context.getRealPath("/WEB-INF/upload");
		// 上传时生成的临时文件保存目录
		String tempPath = context.getRealPath("/WEB-INF/temp");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}
		// 消息提示
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
			factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
			// 设置上传时生成的临时文件的保存目录
			factory.setRepository(tmpFile);
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
			upload.setFileSizeMax(1024 * 1024);
			// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
			upload.setSizeMax(1024 * 1024 * 10);
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项

			for (MultipartFile mf : files) {
				String filename = mf.getOriginalFilename();
				if (filename == null || filename.trim().equals("")) {
					continue;
				}
				// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
				// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
				// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
				filename = filename.substring(filename.lastIndexOf("\\") + 1);
				// 得到上传文件的扩展名
				String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
				// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
				// 获取item中的上传文件的输入流
				InputStream in = mf.getInputStream();
				// 得到文件保存的名称
				String saveFilename = makeFileName(filename);
				// 得到文件的保存目录
				Pair<String, String> path = makePath(request, saveFilename, savePath);
				String realSavePath = path.getFirst();
				// 创建一个文件输出流
				FileOutputStream out = new FileOutputStream(realSavePath + "/" + saveFilename);
				// 创建一个缓冲区
				byte buffer[] = new byte[1024];
				// 判断输入流中的数据是否已经读完的标识
				int len = 0;
				// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while ((len = in.read(buffer)) > 0) {
					// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
					// + filename)当中
					out.write(buffer, 0, len);
				}
				// 关闭输入流
				in.close();
				// 关闭输出流
				out.close();
				// 删除处理文件上传时生成的临时文件
				// item.delete();

				// 保存存放记录
				FilesBo filebo = new FilesBo();
				filebo.setName(filename);
				filebo.setSuffix(fileExtName);
				filebo.setReal_url(realSavePath+"\\"+saveFilename);
				filebo.setUrl(path.getSecond());
				filebo.setUp_user(loginUser.getUsername());
				fileservice.saveFiles(filebo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @Anthor:zhangmin
	 * @param filename
	 *            文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename) { // 2.jpg
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * 
	 * @Method: makePath
	 * @Description:
	 * @Anthor:zhangmin
	 *
	 * @param filename
	 *            文件名，要根据文件名生成存储目录
	 * @param savePath
	 *            文件存储路径
	 * @return 新的存储目录
	 */
	private Pair<String, String> makePath(MultipartHttpServletRequest request, String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		String dir = savePath+ "/" + dir1 + "/" + dir2;
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		String picUrl = "http://" + request.getServerName() + request.getContextPath() + "/upload/" + dir1 + "/" + dir2 + "/" + filename;
		return new Pair<String, String>(dir, picUrl);
	}
}
