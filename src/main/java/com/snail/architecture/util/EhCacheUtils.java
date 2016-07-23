package com.snail.architecture.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ehcache工具类
 * @author snail
 *
 */
public class EhCacheUtils {
	private static final Log logger = LogFactory.getLog(EhCacheUtils.class);
	
	private static Cache localCache;	//ehcache
	private CacheManager  ehcacheManager;

	public void setEhcacheManager(CacheManager ehcacheManager) {
		this.ehcacheManager = ehcacheManager;
	}
	public void init(){
		localCache=ehcacheManager.getCache("common");
	}
	/** 获取缓存key对应的value **/
    public static Object getLocalCache(String key) {
        Object obj = null;
        // 从内存中获取
        Element v = localCache.get(key);
        if (v != null) {
            obj = v.getObjectValue();
        }
        return obj;
    }
    
    /**
     * 批量获取缓存
     * @param keys
     * @return
     */
    public static Map<String, Object> getMultiWithLocalCache(String[] keys) {
    	Map<String, Object> ret = new HashMap<String, Object>();
    	Map<Object, String> kv = new HashMap<Object, String>();
        List<String> goodKeys = new ArrayList<String>();
        for(String key : keys){
        	goodKeys.add(key);
        	kv.put(key, key);
        }
        if(localCache!=null){            
            Map<Object, Element> vs = localCache.getAll(goodKeys);
            if (vs != null) {
            	for(Map.Entry<Object, Element> entry : vs.entrySet()){
            		Element v = entry.getValue();
            		if(v == null){
            			continue;
            		}
            		Object obj = v.getObjectValue();
            		if(obj == null){
            			continue;
            		}
            		ret.put(kv.get(entry.getKey()), obj);
            	}
            }
        }
        return ret;
    }
    
    /**
     * put cache
     * @param key
     * @param value
     * @param expirMins
     */
    public static void putWithLocalCache(String key, Object value, int expirMins) {
    	if (StringUtils.isNotBlank(key)
                && null != value&&localCache!=null) {
            Element e = new Element(key, value);
            e.setTimeToLive(expirMins * 60);
            localCache.put(e);
        } else {
            logger.error("Ehcache Key is blank or value is null! The key is :" + ((key==null)?"":key));
        }
    }
    
}
