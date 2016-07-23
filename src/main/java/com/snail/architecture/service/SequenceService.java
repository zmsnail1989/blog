package com.snail.architecture.service;

public interface SequenceService {
	/**获取当前表序列值
	 * @param tableName
	 * @return
	 */
	public Long getCurrvalSequence(String tableName);
	/**获取下个序列值
	 * @param tableName
	 * @return
	 */
	public Long getNextvalSequence(String tableName);
}
