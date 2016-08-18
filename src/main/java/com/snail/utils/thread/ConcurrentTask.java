/**
 * Copyright NewHeight Co.,Ltd. (C), 2011-2016
 * File Name: ConcurrentTask.java
 * Encoding: UTF-8
 * Date: Apr 23, 2014
 * History: 
 */
package com.snail.utils.thread;

/**
 *
 * @version Revision: 1.00 Date: Apr 23, 2014
 */
public interface ConcurrentTask<T> {
	T process();
}
