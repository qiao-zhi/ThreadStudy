package cn.qlq.thread.eleven;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class Demo11 {
	private ReentrantLock lock = new ReentrantLock();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo11.class);

	public void awaitA() {
		try {
			if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
				LOGGER.info("threadName -> {} , isFair -> " + lock.isFair(), Thread.currentThread().getName());
				LOGGER.info("threadName -> {} ,isLocked -> " + lock.isLocked(), Thread.currentThread().getName());
				LOGGER.info("threadName -> {} ,isHeldByCurrentThread -> " + lock.isHeldByCurrentThread(),
						Thread.currentThread().getName());
				Thread.sleep(6 * 1000);
				// 释放锁
				lock.unlock();
			} else {
				LOGGER.info("threadName -> {} 没有获得锁 ", Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		final Demo11 demo8 = new Demo11();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				demo8.awaitA();
			}
		};
		Thread thread = new Thread(runnable, "thread");
		Thread thread2 = new Thread(runnable, "thread2");
		thread.start();
		thread2.start();
	}
}