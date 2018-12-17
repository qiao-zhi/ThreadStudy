package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class Demo12 {
	private ReentrantLock lock = new ReentrantLock();
	private Condition newCondition = lock.newCondition();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo12.class);

	public void awaitA() {
		LOGGER.info("threadName -> {} 进入方法，等待锁 ", Thread.currentThread().getName());
		try {
			lock.lock();
			LOGGER.info("threadName -> {} begain await ", Thread.currentThread().getName());
			newCondition.await();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Demo12 demo8 = new Demo12();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				demo8.awaitA();
			}
		};
		Thread thread = new Thread(runnable, "thread");
		thread.start();

		Thread.sleep(1 * 1000);
		thread.interrupt();

	}
}