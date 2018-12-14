package cn.qlq.thread.nine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程类join()使用方法--join中中断
 * 
 * @author Administrator
 *
 */
public class Demo3 extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);

	public static void main(String[] args) throws InterruptedException {

		final Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threadA run");
				while (true) {

				}
			}
		}, "threadA");

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threadB run");
				threadA.start();
				try {
					threadA.join(2 * 1000);
				} catch (InterruptedException e) {
					LOGGER.error("join error ,threadName - > {}", Thread.currentThread().getName(), e);
				}
				LOGGER.info("threadB end");
			}
		}, "threadB");
		threadB.start();
	}
}