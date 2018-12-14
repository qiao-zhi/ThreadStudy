package cn.qlq.thread.five;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * volatile用作线程标记
 * 
 * @author Administrator
 *
 */
public class Demo7 {
	private static boolean isThreadA;
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo7.class);

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (isThreadA) {
						LOGGER.info("ThreadName->{}", Thread.currentThread().getName());
						isThreadA = false;
					}
				}
			}
		}, "threadA").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (!isThreadA) {
						LOGGER.info("ThreadName->{}", Thread.currentThread().getName());
						isThreadA = true;
					}
				}
			}
		}, "threadB").start();
	}
}
