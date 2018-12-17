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
public class Demo8 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo8.class);
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
			conditionA.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final Demo8 demo8 = new Demo8();
		Runnable await = new Runnable() {
			public void run() {
				demo8.awaitA();
			}
		};
		new Thread(await, "threadA2").start();
		new Thread(await, "threadA3").start();
		Thread thread = new Thread(await, "threadA1");
		thread.start();

		// 访问signal
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo8.signalA();
			}
		}, "threadS1").start();

		// 返回thread是否在等待获取此锁
		System.out.println(demo8.getLock().hasQueuedThread(thread));
		// 获取是否有等待线程
		System.out.println(demo8.getLock().hasQueuedThreads());
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}
}