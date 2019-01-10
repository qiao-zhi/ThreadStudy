package cn.qlq.thread.tone;

import java.util.concurrent.CyclicBarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class Demo2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

	public static void main(String[] args) throws InterruptedException {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
		for (int i = 0; i < 4; i++) {
			Thread.sleep(2 * 1000);
			new Thread(new Runnable() {
				@Override
				public void run() {
					LOGGER.info("threadName -> {}", Thread.currentThread().getName());
					try {
						cyclicBarrier.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					LOGGER.info("threadName -> {}", Thread.currentThread().getName());
				}
			}).start();
		}
	}
}
