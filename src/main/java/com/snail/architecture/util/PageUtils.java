package com.snail.architecture.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtils {
	/**
	 * 方法说明： *  分页处理类
	 * @param list
	 * @param totalRecord
	 * @param page
	 * @param rows
	 * @return
	 *
	 */
	public static <T> Map<String, Object> wrappQueryResult(List<T> list,int totalRecord){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalRecord);
		map.put("records", list);
		return map;
	}
}
