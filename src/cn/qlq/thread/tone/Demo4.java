package cn.qlq.thread.tone;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class Demo4 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo4.class);

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(5);
		for (int i = 0; i < 5; i++) {
			Thread.sleep(1 * 1000);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						startLatch.await();// 起始闭锁的计数器阻塞等到计数器减到零（标记第一个线程开始执行）
						Thread.sleep(1 * 1000);
						endLatch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		// 实现计时
		long startTime = System.nanoTime();
		startLatch.countDown();// 将起始闭锁的计数器减一
		endLatch.await();// 结束闭锁阻塞直到计数器为零
		long endTime = System.nanoTime();
		LOGGER.error("结束,用时{}", endTime - startTime);
	}
}
