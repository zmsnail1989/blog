package com.snail.architecture.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * spring mongodb　集成操作类　
 * 
 * @author Snail
 * 
 * @param <T>
 */
public class MongoDaoImpl implements IMongoDao {

	 private  static  final Logger logger = LoggerFactory.getLogger(MongoDaoImpl. class);
	@Autowired
	protected MongoTemplate mongoTemplate;
	/**
	 * 保存图片
	 * @param data
	 * @param collectionName
	 * @param fileName
	 * @return
	 */
	public boolean saveFile(byte[] data, String collectionName, String fileName) {
		try {
			DB db = mongoTemplate.getDb();
			// 存储fs的根节点
			GridFS gridFS = new GridFS(db, collectionName);
			GridFSInputFile gfs = gridFS.createFile(data);
			//或者有自定义属性也可以 gfs.put("aliases", companyid);
			gfs.setContentType(fileName.substring(fileName.lastIndexOf(".")));
			gfs.setFilename(fileName);
			gfs.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	// 取出文件
    public GridFSDBFile retrieveFileOne(String collectionName, String filename) {
        try {
            DB db = mongoTemplate.getDb();
            // 获取fs的根节点
            GridFS gridFS = new GridFS(db, collectionName);
            GridFSDBFile dbfile = gridFS.findOne(filename);
            if (dbfile != null) {
                return dbfile;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
