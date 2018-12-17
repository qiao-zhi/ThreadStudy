package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentrantLock結合Condition实现等待/通知，唤醒和等待部分线程
 * 
 * @author Administrator
 *
 */
public class Demo7 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo7.class);
	private ReentrantLock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();

	public void awaitA() {
		try {
			lock.lock();
			Thread.sleep(1 * 1000);
			LOGGER.info("threadName -> {},getWaitQueueLength(conditionA)->{} ", Thread.currentThread().getName(),
					lock.getWaitQueueLength(conditionA));
			conditionA.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signalA() {
		try {
			lock.lock();
			Thread.sleep(1 * 1000);
			LOGGER.info("threadName -> {},getWaitQueueLength(conditionA)->{} ", Thread.currentThread().getName(),
					lock.getWaitQueueLength(conditionA));
			conditionA.signalAll();
			LOGGER.info("threadName -> {},getWaitQueueLength(conditionA)->{} ", Thread.currentThread().getName(),
					lock.getWaitQueueLength(conditionA));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final Demo7 demo7 = new Demo7();
		Runnable await = new Runnable() {
			public void run() {
				demo7.awaitA();
			}
		};
		new Thread(await, "threadA1").start();
		new Thread(await, "threadA2").start();
		new Thread(await, "threadA3").start();

		// 访问signal
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo7.signalA();
			}
		}, "threadS1").start();
	}
}
