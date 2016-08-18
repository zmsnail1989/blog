/**
 * Copyright NewHeight Co.,Ltd. (C), 2011-2016
 * File Name: ConcurrentProcessUtils.java
 * Encoding: UTF-8
 * Date: Apr 23, 2014
 * History: 
 */
package com.snail.utils.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @version Revision: 1.00 Date: Apr 23, 2014
 */
public class ConcurrentProcessUtils {
	private static final int MAX_QUEUE_SIZE = 100000;
	static final Log log = LogFactory.getLog(ConcurrentProcessUtils.class);
	/**
	 * 用于执行同步或异步调用的线程池
	 */
	private static final ExecutorService executor = new ThreadPoolExecutor(30, 100, 60 * 1000, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(MAX_QUEUE_SIZE));
	private static final ExecutorService cacheexecutor = Executors.newCachedThreadPool();
	
	/**
	 * 提交一组任务，同步执行完所有任务后，返回最终结果
	 * <br>注意：该方法提交的任务只能是同步任务，不能是异步的，否则返回的结果不可预测
	 * @param <T>
	 * @param tasks
	 * @param maxWaitInMillion
	 * @return
	 */
	public static <K, T> Map<K, T> concurrentProcess(Map<K, ConcurrentTask<T>> tasks, long maxWaitInMillion){
		int taskSize = tasks.size();
		final CountDownLatch latch = new CountDownLatch(taskSize);
		final Map<K, T> finalResults = new ConcurrentHashMap<K, T>(taskSize*4/3);
		for(final Map.Entry<K, ConcurrentTask<T>> task : tasks.entrySet()){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					ConcurrentTask<T> realTask = task.getValue();
					try{
						T r = realTask.process();
						if(r!=null){
							finalResults.put(task.getKey(), r);
						}
					}catch(Exception e){
						log.error("Execute task failed[taskId:" + task.getKey() + "]" , e);
					}finally{
						latch.countDown();
					}
				}
			});
		}
		try {
			latch.await(maxWaitInMillion, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			log.error("Concurrent process timeout !", e);
			return new HashMap<K, T>(0);
		}
		executor.shutdown();
		return finalResults;
	}

	/**
	 * 提交一组任务，同步执行完所有任务后，返回最终结果 <br>
	 * 注意：该方法提交的任务只能是同步任务，不能是异步的，否则返回的结果不可预测
	 * 
	 * @param <T>
	 * @param tasks
	 * @param maxWaitInMillion
	 * @author zhangmin2@yhd.com
	 * @return
	 */
	public static <K, T> Map<K, T> concurrentProcessNew(Map<K, ConcurrentTask<T>> tasks, long maxWaitInMillion) {
		int taskSize = tasks.size();
		final CyclicBarrier  threadCycle = new CyclicBarrier(taskSize);
		final Map<K, T> finalResults = new ConcurrentHashMap<K, T>(taskSize * 4 / 3);
		for (final Map.Entry<K, ConcurrentTask<T>> task : tasks.entrySet()) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					ConcurrentTask<T> realTask = task.getValue();
					try {
						T r = realTask.process();
						if (r != null) {
							finalResults.put(task.getKey(), r);
						}
					} catch (Exception e) {
						log.error("Execute task failed[taskId:" + task.getKey() + "]", e);
					}
				}
			});
		}
		try {
			threadCycle.await(maxWaitInMillion, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			log.error("ConcurrentProcess concurrent InterruptedException: ");
		} catch (BrokenBarrierException e) {
			log.error("ConcurrentProcess concurrent BrokenBarrierException: ");
		} catch (TimeoutException e) {
			log.error("ConcurrentProcess concurrent TimeoutException: ");
		}
		return finalResults;
	}

	public static <T> Future<T> process(Callable<T> task) {
		return executor.submit(task);
	}

	/**
	 * 
	 * <b><i>异步执行任务</i></b><br>
	 * 
	 * @param tasks
	 *            任务列表
	 * @param timeout
	 *            任务超时时间
	 * @return
	 * @param <K,T>
	 * @date 2015-8-12 下午1:56:24
	 * @description
	 */
	public static <K, T> Map<K, T> asyncExec(Map<K, ConcurrentTask<T>> tasks, long timeout) {
		Map<K, Future<T>> futures = new ConcurrentHashMap<K, Future<T>>();
		for (final Map.Entry<K, ConcurrentTask<T>> entry : tasks.entrySet()) {
			Future<T> future = cacheexecutor.submit(new Callable<T>() {
				public T call() throws Exception {
					return entry.getValue().process();
				}

			});
			futures.put(entry.getKey(), future);
		}
		Map<K, T> result = new HashMap<K, T>();
		for (Map.Entry<K, Future<T>> entry : futures.entrySet()) {
			try {
				T value = entry.getValue().get(timeout, TimeUnit.MILLISECONDS);
				result.put(entry.getKey(), value);
			} catch (InterruptedException e) {
				log.error("ConcurrentProcess async interrupted: ");
				return result;
			} catch (ExecutionException e) {
				log.error("ConcurrentProcess async error: ");
				return result;
			} catch (TimeoutException e) {
				log.error("ConcurrentProcess async timeout: ");
				return result;
			} finally {
				entry.getValue().cancel(true);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Map<Integer, ConcurrentTask<String>> tasks = new HashMap<Integer, ConcurrentTask<String>>();
		int i =1;
		while(i<=200){
			i++;
			final String taskId = ""+i;
			ConcurrentTask<String> task = new ConcurrentTask<String>() {
				@Override
				public String process() {
//					System.out.println("task"+taskId+" process begining!");
					try {
						Thread.sleep(new Random().nextInt(1200));
					} catch (InterruptedException e) {
					}
//					System.out.println("task"+taskId+" process complete!");
					System.out.println(cacheexecutor);
					return "task"+taskId;
				}
			};
			tasks.put(i, task);
		}
		Map<Integer, String> results = asyncExec(tasks, 100000);
		Map<Integer, String> results1 = asyncExec(tasks, 100000);
		Map<Integer, String> results3 = asyncExec(tasks, 100000);
		Map<Integer, String> results4 = asyncExec(tasks, 100000);
		Map<Integer, String> results5 = asyncExec(tasks, 100000);
		Map<Integer, String> results6 = asyncExec(tasks, 100000);
//		try {
//			Thread.sleep(9000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(results.size());
	}
}
