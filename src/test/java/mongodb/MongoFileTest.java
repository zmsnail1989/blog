package mongodb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.gridfs.GridFSDBFile;
import com.snail.architecture.service.IMongoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-mongodb.xml" })
public class MongoFileTest{
	@Autowired
	private IMongoDao mongoDaoImpl;
	/**
	 * 使用mongodb读取文件
	 */
	@Test
	public void saveFile(){
		File file = new File("C:\\Windows\\win.ini");
		String fileName = file.getName();
		byte[] data = null;
		try {
			data = IOUtils.toByteArray(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag =false;
		if (null!=data) {
			flag=mongoDaoImpl.saveFile(data, "fileCollection", fileName);
		}
		Assert.assertEquals(flag, true);
	}
	/**
	 * 读取文件
	 */
	@Test
	public void readFile(){
		GridFSDBFile file=mongoDaoImpl.retrieveFileOne("fileCollection", "win.ini");
		String contentType = file.getContentType();
		System.out.println(contentType);
		InputStream io = file.getInputStream();
		byte[] data = null;
		try {
			data = IOUtils.toByteArray(io);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data.toString());
	}
}
