package cn.qlq.thread.eleven;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class Demo13 {
	private ReentrantLock lock = new ReentrantLock();
	private Condition newCondition = lock.newCondition();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo13.class);

	public void awaitA() {
		LOGGER.info("threadName -> {} 进入方法，等待锁 ", Thread.currentThread().getName());
		try {
			lock.lock();
			LOGGER.info("threadName -> {} begain await ", Thread.currentThread().getName());
			Date deadline = new Date();
			deadline.setSeconds(deadline.getSeconds() + 3);
			newCondition.awaitUntil(deadline);
			LOGGER.info("threadName -> {} end await ", Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Demo13 demo8 = new Demo13();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				demo8.awaitA();
			}
		};
		Thread thread = new Thread(runnable, "thread");
		thread.start();
	}
}