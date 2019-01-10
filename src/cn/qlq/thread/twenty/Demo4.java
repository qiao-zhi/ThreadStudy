package cn.qlq.thread.twenty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo4 {
	/**
	 * 参数是初始化线程池子的大小
	 */
	private static final ExecutorService batchTaskPool = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			batchTaskPool.execute(new MyRunnable());
		}
	}
}
