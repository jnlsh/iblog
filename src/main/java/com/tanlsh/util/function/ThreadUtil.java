package com.tanlsh.util.function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程工具类<br>
 * @author 
 * @version 0.0.1.20141105
 * @history
 * 	0.0.1.20141105
 */
public class ThreadUtil {
	
	/**
	 * 单线程池执行线程
	 * @param runnable
	 */
	public static void runSingle(Runnable runnable){
		run(Executors.newSingleThreadExecutor(), runnable);
	}
	
	/**
	 * 固定大小线程池执行线程
	 * @param runnable
	 */
	public static void runFixed(Runnable runnable, int n){
		run(Executors.newFixedThreadPool(n), runnable);
	}
	
	/**
	 * 可变大小线程池执行线程
	 * @param runnable
	 */
	public static void runCached(Runnable runnable){
		run(Executors.newCachedThreadPool(), runnable);
	}
	
	/**
	 * 定时线程池执行线程
	 * @param runnable
	 */
	public static void runScheduled(Runnable runnable, int n){
		run(Executors.newScheduledThreadPool(n), runnable);
	}
	
	/**
	 * 线程池执行线程
	 * @param executor
	 * @param runnable
	 */
	private static void run(ExecutorService executor, Runnable runnable){
		executor.execute(runnable);
		executor.shutdown();
	}
	
}
