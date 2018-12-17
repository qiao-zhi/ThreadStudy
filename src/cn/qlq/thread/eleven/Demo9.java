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
public class Demo9 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo9.class);
	private ReentrantLock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();

	public void awaitA() {
		try {
			lock.lock();
			Thread.sleep(1 * 1000);
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
			System.out.println(lock.hasWaiters(conditionA));
			conditionA.signalAll();
			System.out.println(lock.hasWaiters(conditionA));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final Demo9 demo8 = new Demo9();
		Runnable await = new Runnable() {
			public void run() {
				demo8.awaitA();
			}
		};
		Thread thread = new Thread(await, "threadA1");
		thread.start();
		new Thread(await, "threadA2").start();
		new Thread(await, "threadA3").start();

		// 访问signal
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo8.signalA();
			}
		}, "threadS1").start();
	}
}
