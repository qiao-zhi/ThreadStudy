package cn.qlq.thread.six;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * wait(long)自动唤醒
 * 
 * @author Administrator
 *
 */
public class Demo8 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo8.class);

	public static void main(String[] args) throws InterruptedException {
		final Object object = new Object();

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (object) {
					try {
						Thread.sleep(5 * 1000);
						LOGGER.info("wait start---------------,threadName->{}", Thread.currentThread().getName());
						// 等待10秒钟自动唤醒
						object.wait(5 * 1000);
						LOGGER.info("wait end---------------,threadName->{}", Thread.currentThread().getName());
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						LOGGER.error("InterruptedException error", e);
					}
				}
			}
		}, "A");
		threadA.start();

		Thread.sleep(2 * 1000);
		LOGGER.info("threadA state->{}", threadA.getState());
		Thread.sleep(5 * 1000);
		LOGGER.info("threadA state->{}", threadA.getState());
		Thread.sleep(5 * 1000);
		LOGGER.info("threadA state->{}", threadA.getState());
		Thread.sleep(5 * 1000);
		LOGGER.info("threadA state->{}", threadA.getState());
	}
}
