package com.snail.utils.hbase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * hbase 查询客户端
 * </pre>
 * 
 */
public class HBaseClient {

	private static final Logger logger = LoggerFactory.getLogger(HBaseClient.class);
	private HConnection hTablePool;
	// private HBaseAdmin hBaseAdmin;
	private String fileName; // 配置文件hbase-site.xml
	private static final List<byte[]> EMPTY_QUALIFIERS = new ArrayList<byte[]>(); // 当不指定
	private long writeBufferSize; // 写入hbase的缓存区大小

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void sethTablePool(HConnection hTablePool) {
		if (hTablePool != null) {
			this.hTablePool = hTablePool;
		}
	}

	public void init() {
		// String classpath =
		// Thread.currentThread().getContextClassLoader().getResource("").getPath();
		try {
			String hbaseSitePath = "";

			// String path = System.getProperty("global.config.path");
			File file = new File(hbaseSitePath);
			if (!file.exists()) {
				logger.error("could not found the file " + file.getAbsolutePath() + ", tomcat could not start up . ");
				throw new RuntimeException("could not found the file " + file.getAbsolutePath());
			}

			Configuration conf = new Configuration();
			Path p = new Path(hbaseSitePath);
			conf.addResource(p);
			// hBaseAdmin = new HBaseAdmin(conf);
			hTablePool = HConnectionManager.createConnection(conf);
			// 设置默认的写入缓存大小
			writeBufferSize = 100l;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	// public ResultScanner query(String tableName, byte[] family) throws
	// Exception {
	// // HTable table = poolManager.getHtableNotAutoFlush(tableName);
	// HTableInterface table = hTablePool.getTable(tableName);
	// ResultScanner scanner = table.getScanner(family);
	// return scanner;
	// }

	/**
	 * 根据表名、rowkey、family查询出的包含当前行的该列族的所有值
	 * 
	 * @param tableName
	 * @param rowkey
	 * @param family
	 * @return List<Cell>
	 * @throws Exception
	 * @author on 2014.8.8
	 */
	public List<Cell> list(String tableName, byte[] rowkey, byte[] family) throws Exception {
		Result result = queryByRowWithoutQualifier(tableName, rowkey, family);
		return result.listCells();
	}

	/**
	 * 扫描一张表的指定列族的所有行
	 * 
	 * @param tableName
	 * @param family
	 * @return Map<byte[], Map<byte[], byte[]>>
	 * @throws Exception
	 * @author on 2014.8.8
	 */
	public Map<byte[], Map<byte[], byte[]>> scan(String tableName, byte[] family) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		ResultScanner scanner = table.getScanner(new Scan());
		Iterator<Result> iterator = scanner.iterator();
		Map<byte[], Map<byte[], byte[]>> map = new HashMap<byte[], Map<byte[], byte[]>>();
		while (iterator.hasNext()) {
			Result result = iterator.next();
			map.put(result.getRow(), result.getFamilyMap(family));
		}
		scanner.close();
		return map;
	}

	/**
	 * 不支持传入filter的查询
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @param qualifiers
	 * @return Map<String, String>
	 * @throws Exception
	 * @author on 2014.8.8
	 */
	private Map<String, String> queryByRowToMap(String tableName, byte[] row, byte[] family, List<byte[]> qualifiers)
			throws Exception {
		Map<String, String> columnAndValueMap = new HashMap<String, String>();
		if (checkQualifierIsValid(qualifiers)) {
			// 查询列不为空
			List<byte[]> unhitedQualifiers = new ArrayList<byte[]>();
			for (byte[] qualifier : qualifiers) {
				unhitedQualifiers.add(qualifier);
			}
			if (!CollectionUtils.isEmpty(unhitedQualifiers)) {
				Result result = queryByRowWithQualifier(tableName, row, family, unhitedQualifiers);
				Map<String, String> changeMap = changeMap(result.getFamilyMap(family));
				for (byte[] unhitedQualifier : unhitedQualifiers) {
					String unhitedQualifierKey = new String(unhitedQualifier);
					String columnValue = changeMap.get(unhitedQualifierKey);
					columnAndValueMap.put(unhitedQualifierKey, columnValue);
				}
			}
		} else {
			// 查询该列族下的所有列
			Result result = queryByRowWithoutQualifier(tableName, row, family);
			Map<String, String> map = changeMap(result.getFamilyMap(family));
			columnAndValueMap.putAll(map);
		}
		return columnAndValueMap;
	}

	/**
	 * 根据表名、行key名、列族名查询结果
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @return {@link Result}
	 * @throws Exception
	 * @author on 2014.8.12
	 */
	private Result queryByRowWithoutQualifier(String tableName, byte[] row, byte[] family) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		Get get = new Get(row);
		get.addFamily(family);
		Result result = table.get(get);
		return result;
	}

	/**
	 * 根据表名、行key名、列族名、指定列名集合等查询结果
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @param qualifiers
	 * @return {@link Result}
	 * @throws Exception
	 * @author on 2014.8.12
	 */
	private Result queryByRowWithQualifier(String tableName, byte[] row, byte[] family, List<byte[]> qualifiers)
			throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		Get get = new Get(row);
		for (byte[] qualifier : qualifiers) {
			get.addColumn(family, qualifier);
		}
		Result result = table.get(get);
		return result;
	}

	private Result queryByRowWithQualifier(String tableName, byte[] row, byte[] family, List<byte[]> qualifiers,
			Integer versionNum) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		Get get = new Get(row);
		for (byte[] qualifier : qualifiers) {
			get.addColumn(family, qualifier);
		}
		if (versionNum != null && versionNum < Integer.MAX_VALUE) {
			get.setMaxVersions(versionNum);
		}
		Result result = table.get(get);
		return result;
	}

	/**
	 * 按照单行rowkey、列族名、列名集合等查询结果
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @param qualifiers
	 * @param filters
	 * @return Result[]
	 * @throws Exception
	 * @author on 2014.8.12
	 */
	private Map<String, String> queryByRowWithQualifierAndFilter(String tableName, byte[] row, byte[] family,
			List<byte[]> qualifiers, List<Filter> filters) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		List<Get> gets = new ArrayList<Get>();
		for (Filter filter : filters) {
			Get get = new Get(row);
			get.setFilter(filter);
			for (byte[] qualifier : qualifiers) {
				get.addColumn(family, qualifier);
			}
			gets.add(get);
		}
		Result[] results = table.get(gets);
		return changeResultArrayToMapWithSingleRow(results, family);
	}

	/**
	 * 按照单行rowkey、列族名、列名集合、filter集合等查询结果
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @param filters
	 * @return {@link Map}
	 * @throws Exception
	 * @author on 2014.8.12
	 */
	private Map<String, String> queryByRowWithFilterButNotQualifier(String tableName, byte[] row, byte[] family,
			List<Filter> filters) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		List<Get> gets = new ArrayList<Get>();
		for (Filter filter : filters) {
			Get get = new Get(row);
			get.setFilter(filter);
			get.addFamily(family);
			gets.add(get);
		}
		Result[] results = table.get(gets);
		return changeResultArrayToMapWithSingleRow(results, family);

	}

	/**
	 * 将{@link Result}的数组转换成{@link Map}<br/>
	 * 适应于单行
	 * 
	 * @param resultArray
	 * @param family
	 * @return Map<String,String>
	 * @author on 2014.8.12
	 */
	private Map<String, String> changeResultArrayToMapWithSingleRow(Result[] resultArray, byte[] family) {
		List<Cell> keyValues = new ArrayList<Cell>();
		for (Result result : resultArray) {
			if (result.listCells() == null || result.listCells().size() == 0) {
				continue;
			}
			keyValues.addAll(result.listCells());
		}
		Result result = Result.create(keyValues);
		NavigableMap<byte[], byte[]> familyMap = result.getFamilyMap(family);
		Map<String, String> changeMap = changeMap(familyMap);
		return changeMap;
	}

	private static Map<String, String> changeMap(Map<byte[], byte[]> bytesMap) {
		if (bytesMap.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<String, String> stringMap = new HashMap<String, String>();
		Set<Entry<byte[], byte[]>> entrySet = bytesMap.entrySet();
		for (Entry<byte[], byte[]> entry : entrySet) {
			byte[] key = entry.getKey();
			byte[] value = entry.getValue();
			if (key != null && value != null) {
				stringMap.put(Bytes.toString(key), Bytes.toString(value));
			}
		}
		return stringMap;
	}

	/**
	 * 按单个row查询的方法，支持传入一组filter<br>
	 * <br>
	 * <b><i>使用此方法请注意，如果传入列选项为空，会查出整行记录，请判断此种场景是否是当前业务需要</i></b><br>
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @param qualifiers
	 * @param filters
	 * @return Map<String, String>
	 * @throws Exception
	 * @author on 2014.8.8
	 */
	public Map<String, String> queryBySingleRow(String tableName, byte[] row, byte[] family, List<byte[]> qualifiers,
			List<Filter> filters) throws Exception {
		if (CollectionUtils.isEmpty(filters)) {
			return queryByRowToMap(tableName, row, family, qualifiers);
		}
		Map<String, String> result = new HashMap<String, String>();
		if (checkQualifierIsValid(qualifiers)) {
			List<byte[]> unhitedQualfierList = new ArrayList<byte[]>();
			for (byte[] qualifier : qualifiers) {
				unhitedQualfierList.add(qualifier);
			}
			if (!CollectionUtils.isEmpty(unhitedQualfierList)) {
				Map<String, String> map = queryByRowWithQualifierAndFilter(tableName, row, family, unhitedQualfierList,
						filters);
				for (byte[] qualifier : unhitedQualfierList) {
					String columnKey = new String(qualifier);
					String columnValue = map.get(columnKey);
					result.put(columnKey, columnValue);
				}
			}
		} else {
			Map<String, String> map = queryByRowWithFilterButNotQualifier(tableName, row, family, filters);
			if (map != null && !map.isEmpty()) {
				result.putAll(map);
			}
		}
		return result;
	}

	/**
	 * 检查qualifier 列名数组是否有效
	 * 
	 * @param qualifiers
	 * @return boolean
	 * @author on 2014.8.8
	 */
	public boolean checkQualifierIsValid(List<byte[]> qualifiers) {
		return CollectionUtils.isNotEmpty(qualifiers) && qualifiers.get(0) != null;
	}

	// public Result[] queryByRows(String tableName, List<byte[]> rows, byte[]
	// family,Filter filter) throws Exception {
	// HTableInterface table = hTablePool.getTable(tableName);
	// List<Get> gets = new ArrayList<Get>();
	// for (byte[] row : rows) {
	// Get get = new Get(row);
	// get.addFamily(family);
	// get.setFilter(filter);
	// gets.add(get);
	// }
	// Result[] result = table.get(gets);
	// return result;
	// }

	/**
	 * 根据表名、rowkey数组，列簇，指定列名等来查询数据<br/>
	 * 返回结果是rowkey和由列名和列值对应组成的map,所有rowkey对应的查询列都相同，都为qualifieres
	 * 
	 * @param tableName
	 *            表名
	 * @param rows
	 *            rowkey数组
	 * @param family
	 *            列簇名
	 * @param qualifieres
	 *            指定列名数组
	 * @return Map<String,Map<String,String>>
	 * @throws Exception
	 * @author on 2014.6.13
	 */
	public Map<String, Map<String, String>> queryWithMultiRows(String tableName, List<byte[]> rows, byte[] family,
			List<byte[]> qualifieres, Filter filter) throws Exception {
		if (CollectionUtils.isEmpty(qualifieres)) {
			qualifieres = EMPTY_QUALIFIERS;
		}
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		List<byte[]> unhitedRows = new ArrayList<byte[]>();
		for (byte[] row : rows) {
			unhitedRows.add(row);
		}
		if (!CollectionUtils.isEmpty(unhitedRows)) {
			List<Get> gets = new ArrayList<Get>();
			for (byte[] row : rows) {
				Get get = new Get(row);
				if (checkQualifierIsValid(qualifieres)) {
					for (byte[] qualifier : qualifieres) {
						get.addColumn(family, qualifier);
					}
				} else {
					get.addFamily(family);
				}
				if (filter != null) {
					get.setFilter(filter);
				}
				gets.add(get);
			}
			Map<String, Map<String, String>> resultMap = queryWithMultiRows(gets, tableName, family);
			for (byte[] unhitedRow : unhitedRows) {
				String unhitedRowKey = new String(unhitedRow);
				Map<String, String> map = resultMap.get(unhitedRowKey);
				result.put(unhitedRowKey, map);
			}
		}
		return result;

	}

	/**
	 * 根据表名、rowkey数组，列簇，指定列名等来查询数据<br/>
	 * 返回结果是rowkey和由列名和列值对应组成的map,所有rowkey对应的查询列都相同，都为qualifieres
	 * queryWithMultiRows no cache版本，对应用户意图
	 * 
	 * @param tableName
	 *            表名
	 * @param rows
	 *            rowkey数组
	 * @param family
	 *            列簇名
	 * @param qualifieres
	 *            指定列名数组
	 * @return Map<String,Map<String,String>>
	 * @throws Exception
	 * @author on 2014.6.13
	 */
	public Map<String, Map<String, String>> queryWithMultiRowsNoCache(String tableName, List<byte[]> rows,
			byte[] family, List<byte[]> qualifieres, Filter filter) throws Exception {
		if (CollectionUtils.isEmpty(qualifieres)) {
			qualifieres = EMPTY_QUALIFIERS;
		}
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();

		if (!CollectionUtils.isEmpty(rows)) {
			List<Get> gets = new ArrayList<Get>();
			for (byte[] row : rows) {
				Get get = new Get(row);
				if (checkQualifierIsValid(qualifieres)) {
					for (byte[] qualifier : qualifieres) {
						get.addColumn(family, qualifier);
					}
				} else {
					get.addFamily(family);
				}
				if (filter != null) {
					get.setFilter(filter);
				}
				gets.add(get);
			}
			result = queryWithMultiRows(gets, tableName, family);
		}
		return result;
	}

	/**
	 * 根据表名tableName、列族名familyName,由rowkey和该行对应的独特的列名集合组成的map等查询<br/>
	 * 如果所有的rowkey对应的列名都一样，推荐使用
	 * {@link #queryWithMultiRows(String, List, byte[], List, Filter)}方法
	 * 
	 * @param tableName
	 *            表名
	 * @param familyName
	 *            列族名
	 * @param qualifierMap
	 *            由rowkey和列名集合组成的map
	 * @param filter
	 *            过滤器
	 * @param cacheTime
	 *            缓存时间,当缓存时间小于等于0时候，不做缓存
	 * @return Map<String,Map<String,String>> key为rowkey,值为列名和value组成的map
	 * @throws IOException
	 *             当表查询不到的时候抛出IO异常
	 * @author on 2014.9.19
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> queryByMultiRowsWithDifferentQualifiers(String tableName, byte[] familyName,
			Map<byte[], List<byte[]>> qualifierMap, Filter filter, int cacheTime) throws IOException {
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
		if (qualifierMap == null || qualifierMap.isEmpty()) {
			return Collections.EMPTY_MAP;
		}
		Set<Entry<byte[], List<byte[]>>> unhitedRows = new HashSet<Entry<byte[], List<byte[]>>>();
		Set<Entry<byte[], List<byte[]>>> entrySet = qualifierMap.entrySet();
		for (Entry<byte[], List<byte[]>> entry : entrySet) {
			unhitedRows.add(entry);
		}
		// 查看是否有未被命中的row
		if (CollectionUtils.isNotEmpty(unhitedRows)) {
			List<Get> gets = new ArrayList<Get>();
			for (Entry<byte[], List<byte[]>> entry : unhitedRows) {
				byte[] row = entry.getKey();
				List<byte[]> qualifieres = entry.getValue();
				Get get = new Get(row);
				if (checkQualifierIsValid(qualifieres)) {
					for (byte[] qualifier : qualifieres) {
						get.addColumn(familyName, qualifier);
					}
				} else {
					get.addFamily(familyName);
				}
				if (filter != null) {
					get.setFilter(filter);
				}
				gets.add(get);
			}
			Map<String, Map<String, String>> resultMap = queryWithMultiRows(gets, tableName, familyName);
			for (Entry<byte[], List<byte[]>> entry : unhitedRows) {
				byte[] unhitedRowKey = entry.getKey();
				String unhitedRow = Bytes.toString(unhitedRowKey);
				Map<String, String> map = resultMap.get(unhitedRow);
				result.put(unhitedRow, map);
			}
		}
		return result;
	}

	private Map<String, Map<String, String>> queryWithMultiRows(List<Get> gets, String tableName, byte[] familyName)
			throws IOException {
		HTableInterface table = hTablePool.getTable(tableName);
		Result[] resultList = table.get(gets);
		Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
		if (resultList != null && resultList.length > 0) {
			for (Result resultItem : resultList) {
				if (resultItem.getRow() == null) {
					continue;
				}
				String rowKey = Bytes.toString(resultItem.getRow());
				NavigableMap<byte[], byte[]> familyMap = resultItem.getFamilyMap(familyName);
				Map<String, String> columnMap = changeMap(familyMap);
				resultMap.put(rowKey, columnMap);
			}
		}
		return resultMap;
	}

	// public ResultScanner scanTable(String tableName, Scan scan) throws
	// Exception {
	// // HTable table = poolManager.getHtableNotAutoFlush(tableName);
	// HTableInterface table = hTablePool.getTable(tableName);
	// ResultScanner scanner = table.getScanner(scan);
	// return scanner;
	// }
	//
	// public Result get(String tableName, Get get) throws Exception {
	// // HTable table = poolManager.getHtableNotAutoFlush(tableName);
	// HTableInterface table = hTablePool.getTable(tableName);
	// Result result = table.get(get);
	// // poolManager.relaseHtable(table);
	// return result;
	// }

	// public ResultScanner queryByPage(String tableName, byte[] family,
	// FilterList filterList, int pageNo, int
	// pageSize,
	// Map<String, Set<String>> fields) throws Exception {
	// // HTable table = poolManager.getHtableNotAutoFlush(tableName);
	// HTableInterface table = hTablePool.getTable(tableName);
	// byte[] startRow = null;
	// boolean fisrtPage = true;
	//
	// if (pageNo != INDEX_PAGE) {
	// FilterList filters = new FilterList();
	//
	// for (Filter f : filterList.getFilters()) {
	// filters.addFilter(f);
	// }
	//
	// startRow = getStartRow(table, filters, pageNo, pageSize);
	// fisrtPage = false;
	// }
	//
	// Scan scan = new Scan();
	// if (startRow != null) {
	// scan.setStartRow(startRow);
	// }
	//
	// if (family != null) {
	// scan.addFamily(family);
	// }
	//
	// if (fields != null) {
	// for (Entry<String, Set<String>> field : fields.entrySet()) {
	// Set<String> qualifier = field.getValue();
	// final String fm = field.getKey();
	// for (String col : qualifier) {
	// scan.addColumn(Bytes.toBytes(fm), Bytes.toBytes(col));
	// }
	// }
	// }
	//
	// if (fisrtPage) {
	// filterList.addFilter(new PageFilter(pageSize));
	// } else {
	// filterList.addFilter(new PageFilter(pageSize + 1));
	// }
	//
	// scan.setFilter(filterList);
	// ResultScanner scanner = table.getScanner(scan);
	// // Iterator<Result> iter = scanner.iterator();
	//
	// // 如果不是第一页 需要过滤查询结果的第一条记录 因为这是多余的一条记录
	// // if (!fisrtPage) {
	// // while (iter.hasNext()) {
	// // iter = scanner.iterator();
	// // break;
	// // }
	// // }
	//
	// // poolManager.relaseHtable(table);
	// return scanner;
	// }
	//
	// private byte[] getStartRow(HTableInterface table, FilterList filterList,
	// int pageNo, int pageSize)
	// throws IOException {
	// // 根据pageSize 与pageNo 找出startRow
	// // 假如现在是在第一页 ，然后点击了第3页
	// // 则 应该过滤掉 pageSize * pageNo 条记录数
	// Scan scan = new Scan();
	// filterList.addFilter(new PageFilter((pageNo - 1) *
	// Long.valueOf((pageSize))));
	// filterList.addFilter(new FirstKeyOnlyFilter());
	// scan.setFilter(filterList);
	//
	// ResultScanner scanner = table.getScanner(scan);
	//
	// Iterator<Result> ite = scanner.iterator();
	// byte[] lastRow = null;
	// while (ite.hasNext()) {
	// Result result = ite.next();
	// lastRow = result.getRow();
	// }
	// scanner.close();
	// logger.info("lastRow : " + Bytes.toString(lastRow));
	// return lastRow;
	// }

	// @SuppressWarnings("deprecation")
	// public void createTable(String tableName, List<String> families) throws
	// Exception {
	// if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
	// dropTable(tableName);
	// logger.info(tableName + " is exist,detele....");
	// }
	// HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
	// for (String family : families) {
	// tableDescriptor.addFamily(new HColumnDescriptor(family));
	// }
	// hBaseAdmin.createTable(tableDescriptor);
	// }
	//
	// public void dropTable(String tableName) throws Exception {
	// hBaseAdmin.disableTable(tableName);
	// hBaseAdmin.deleteTable(tableName);
	// }

	/**
	 * 根据tableName、列族等插入多行多列数据<br/>
	 * 采用自动刷新等默认配置
	 * 
	 * @param tableName
	 * @param family
	 * @param map
	 * @throws Exception
	 * @author on 2014.8.12
	 */
	public void insertDataWithAutoFlush(String tableName, byte[] family, Map<byte[], Map<byte[], byte[]>> map)
			throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		List<Put> list = insertData(table, true, tableName, family, map);
		if (CollectionUtils.isNotEmpty(list)) {
			table.put(list);
		}
	}

	/**
	 * 根据tableName、列族等插入多行多列数据<br/>
	 * 采用关闭自动刷新、设置写入缓存大小、关闭写入HLog等办法
	 * 
	 * @param tableName
	 * @param family
	 * @param map
	 * @throws Exception
	 * @author on 2014.8.12
	 */
	public void insertDataWithoutAutoFlush(String tableName, byte[] family, Map<byte[], Map<byte[], byte[]>> map)
			throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);

		// Put put = new Put(Bytes.toBytes("123"));
		// put.add(family, Bytes.toBytes("123"), Bytes.toBytes("2016-07-01"));
		// table.put(put);table.close();

		// 设置自动刷新为false，并且当写入失败时候，自动清空缓存
		table.setAutoFlush(false, true);
		// 设置自动写入时候的缓存大小为32M
		table.setWriteBufferSize(writeBufferSize);
		//
		// 同时关闭
		List<Put> list = insertData(table, false, tableName, family, map);
		if (CollectionUtils.isNotEmpty(list)) {
			table.put(list);

		}
		table.flushCommits();
	}

	/**
	 * 插入多行多列数据
	 * 
	 * @param tableName
	 * @param family
	 * @param map
	 *            <行-<列-值>>对照关系
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	private List<Put> insertData(HTableInterface table, boolean canWriteToWal, String tableName, byte[] family,
			Map<byte[], Map<byte[], byte[]>> map) {
		if (map == null || CollectionUtils.isEmpty(map.entrySet())) {
			return Collections.EMPTY_LIST;
		}
		List<Put> list = new ArrayList<Put>();
		Set<Entry<byte[], Map<byte[], byte[]>>> rowSet = map.entrySet();
		// 循环行
		for (Entry<byte[], Map<byte[], byte[]>> row : rowSet) {

			Map<byte[], byte[]> colMap = row.getValue();
			if (row.getKey() == null || colMap == null || CollectionUtils.isEmpty(colMap.entrySet())) {
				continue;
			}
			Put put = new Put(row.getKey());
			// 是否设置关闭写入wal
			if (!canWriteToWal) {
				put.setWriteToWAL(false);
			}
			Set<Entry<byte[], byte[]>> columSet = colMap.entrySet();
			for (Entry<byte[], byte[]> colum : columSet) {
				if (colum.getKey() != null) {
					put.add(family, colum.getKey(), colum.getValue());

				}
			}
			list.add(put);
		}
		return list;
	}

	/**
	 * 删除多行数据
	 * 
	 * @param tableName
	 * @param family
	 * @param map
	 *            <行-<列-值>>对照关系
	 * @throws Exception
	 */
	public void deleteData(String tableName, byte[] family, Map<byte[], Map<byte[], byte[]>> map) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		if (map == null || CollectionUtils.isEmpty(map.entrySet())) {
			return;
		}
		List<Delete> list = new ArrayList<Delete>();
		Set<Entry<byte[], Map<byte[], byte[]>>> rowSet = map.entrySet();
		// 循环行
		for (Entry<byte[], Map<byte[], byte[]>> row : rowSet) {
			Map<byte[], byte[]> colMap = row.getValue();
			if (row.getKey() == null || colMap == null || CollectionUtils.isEmpty(colMap.entrySet())) {
				continue;
			}
			Delete delete = new Delete(row.getKey());
			Set<Entry<byte[], byte[]>> columSet = colMap.entrySet();
			for (Entry<byte[], byte[]> colum : columSet) {
				if (colum.getKey() != null) {
					delete.deleteColumn(family, colum.getKey());
				}
			}
			list.add(delete);
		}
		table.delete(list);
	}

	/**
	 * 根据表名、rowkey查询出的包含当前行的所有列
	 * 
	 * @param tableName
	 * @param rowkey
	 * @return List<Map<String, String>>
	 * @throws Exception
	 * @author zhangxiaofa@yihaodian.com on 2015.7.01
	 */
	public Map<String, String> queryByRow(String tableName, byte[] rowkey) throws Exception {
		HTableInterface table = hTablePool.getTable(tableName);
		Get get = new Get(rowkey);
		Result result = table.get(get);
		List<Cell> listCells = result.listCells();
		Map<String, String> qualifierMap = new HashMap<String, String>();
		// 检查返回的列是否为空
		if (!CollectionUtils.isEmpty(listCells)) {
			String qualifier;
			String value;
			for (Cell cell : listCells) {
				qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
				value = Bytes.toString(CellUtil.cloneValue(cell));
				qualifierMap.put(qualifier, value);
			}
		} else {
			return Collections.emptyMap();
		}
		return qualifierMap;
	}

	/**
	 * 
	 * <b><i>根据多个rowkey查询结果Result</i></b><br>
	 * 
	 * @param tableName
	 *            表名
	 * @param rows
	 *            rowkey列表
	 * @param family
	 *            列族
	 * @param cacheTime
	 *            缓存时间
	 * 
	 * @return {@link List}<{@link Result}> 结果Result列表
	 *
	 * @author lvyanlong
	 * @date 2014-12-18 上午9:48:31
	 * @description
	 */
	public List<Result> queryResultWithMutiRows(String tableName, List<byte[]> rows, byte[] family, int cacheTime)
			throws Exception {
		List<Result> resultList = new ArrayList<Result>();
		HTableInterface table = hTablePool.getTable(tableName);
		List<Get> gets = new ArrayList<Get>();
		for (byte[] row : rows) {
			Get get = new Get(row);
			get.addFamily(family);
			gets.add(get);
		}
		Result[] results = table.get(gets);
		if (results != null && results.length > 0) {
			for (Result result : results) {
				resultList.add(result);
			}
		}

		return resultList;
	}

	/**
	 * 
	 * <b><i>根据多个rowkey查询结果Result</i></b><br>
	 * 
	 * @param tableName
	 *            表名
	 * @param rows
	 *            rowkey列表
	 * @param family
	 *            列族
	 * @param cacheTime
	 *            缓存时间
	 * 
	 * @return {@link List}<{@link Result}>
	 * 
	 * @throws Exception
	 *
	 * @author lvyanlong
	 * @date 2014-12-18 上午11:13:03
	 * @description
	 */
	public List<Result> queryResultWithMutiRows(String tableName, List<String> rows, String family, int cacheTime)
			throws Exception {
		if (CollectionUtils.isEmpty(rows)) {
			return null;
		}
		List<byte[]> byteRows = new ArrayList<byte[]>();
		for (String row : rows) {
			byteRows.add(Bytes.toBytes(row));
		}
		return queryResultWithMutiRows(tableName, byteRows, family.getBytes(), cacheTime);
	}

	/**
	 * 查询单列多版本列值
	 * 
	 * @param tableName
	 * @param row
	 * @param family
	 * @param qualifier
	 * @param versions
	 *            获取的版本数
	 * @return 每个版本值之间用“_”符号分隔
	 */
	public String querySingleRowByMultiVersions(String tableName, byte[] row, byte[] family, byte[] qualifier,
			Integer versions) {

		StringBuilder key = new StringBuilder("pms_querySingleRowByMultiVersions_");
		String returnResult = null;
		if (StringUtils.isNotBlank(tableName)) {
			key.append("_").append(tableName);
		}
		if (row != null && row.length > 0) {
			key.append("_").append(new String(row));
		}
		if (family != null && family.length > 0) {
			key.append("_").append(new String(family));
		}
		if (qualifier != null && qualifier.length > 0) {
			key.append("_").append(new String(qualifier));

		}
		Result result = null;
		List<byte[]> qualifielList = new ArrayList<byte[]>();
		qualifielList.add(qualifier);
		try {
			result = queryByRowWithQualifier(tableName, row, family, qualifielList, versions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Cell> list = result.listCells();
		StringBuilder sb = new StringBuilder("");
		if (CollectionUtils.isNotEmpty(list)) {
			for (Cell cell : list) {
				String values = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
				sb.append(values).append("_");
			}
		}
		returnResult = sb.toString();
		return returnResult;
	}
}
