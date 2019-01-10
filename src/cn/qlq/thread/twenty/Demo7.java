package cn.qlq.thread.twenty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo7 {
	private static volatile AtomicInteger atomicInteger = new AtomicInteger();
	/**
	 * 参数是初始化线程池子的大小
	 */
	private static final ExecutorService batchTaskPool = Executors.newFixedThreadPool(2, new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setName("t" + atomicInteger.incrementAndGet());
			thread.setDaemon(true);// 设置为守护线程
			return thread;
		}
	});

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			batchTaskPool.execute(new MyRunnable());
		}
		// 必须休眠。否则创建的是守护线程会直接关闭进程
		Thread.sleep(20 * 1000);
	}
}
