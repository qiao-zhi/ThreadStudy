package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentrantLock結合Condition实现等待/通知
 * 
 * @author Administrator
 *
 */
public class Demo2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();

	public void await() {
		try {
			lock.lock();
			LOGGER.info("threadName -> {} start await", Thread.currentThread().getName());
			condition1.await();
			LOGGER.info("threadName -> {} end await", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LOGGER.info("threadName -> {} unlock", Thread.currentThread().getName());
			lock.unlock();
		}
		LOGGER.info("threadName -> {} exit await 退出await方法", Thread.currentThread().getName());
	}

	public void signal() {
		try {
			lock.lock();
			LOGGER.info("threadName -> {} start signal", Thread.currentThread().getName());
			condition1.signal();
			LOGGER.info("threadName -> {} end signal", Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOGGER.info("threadName -> {} unlock", Thread.currentThread().getName());
			lock.unlock();
		}
		LOGGER.info("exit signal 退出signal方法");
	}

	public static void main(String[] args) {
		final Demo2 demo2 = new Demo2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo2.await();
			}
		}, "threadA").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo2.signal();
			}
		}, "threadB").start();
	}
}
