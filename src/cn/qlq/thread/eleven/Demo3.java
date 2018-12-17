package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentrantLock結合Condition实现等待/通知，唤醒和等待部分线程
 * 
 * @author Administrator
 *
 */
public class Demo3 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);
	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();

	public void awaitA() {
		try {
			lock.lock();
			LOGGER.info("threadName -> {} start await", Thread.currentThread().getName());
			Thread.sleep(1 * 1000);
			conditionA.await();
			LOGGER.info("threadName -> {} end await", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signalA() {
		try {
			lock.lock();
			LOGGER.info("threadName -> {} start signal", Thread.currentThread().getName());
			Thread.sleep(1 * 1000);
			conditionA.signal();
			LOGGER.info("threadName -> {} end signal", Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void awaitB() {
		try {
			lock.lock();
			LOGGER.info("threadName -> {} start await", Thread.currentThread().getName());
			Thread.sleep(1 * 1000);
			conditionB.await();
			LOGGER.info("threadName -> {} end await", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signalB() {
		try {
			lock.lock();
			LOGGER.info("threadName -> {} start signal", Thread.currentThread().getName());
			Thread.sleep(1 * 1000);
			conditionB.signal();
			LOGGER.info("threadName -> {} end signal", Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final Demo3 demo3 = new Demo3();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo3.awaitA();
			}
		}, "threadA").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo3.signalA();
			}
		}, "threadA1").start();
		// 访问conditionB
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo3.awaitB();
			}
		}, "threadB").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo3.signalB();
			}
		}, "threadB1").start();
	}
}
