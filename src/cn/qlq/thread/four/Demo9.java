package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo9 extends Thread {

	public static void main(String[] args) throws InterruptedException {
		final SyncObj10 syncObj10 = new SyncObj10();
		// 访问同步代码块占用锁
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj10.syncMethod1();
			}
		}, "A").start();
		Thread.sleep(1 * 1000);
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj10.syncMethod2();
			}
		}, "B").start();
		Thread.sleep(1 * 1000);
		// 访问普通方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj10.syncMethod4();
			}
		}, "C").start();
	}
}

class SyncObj10 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj10.class);

	/**
	 * 同步代码块
	 */
	public void syncMethod1() {
		try {
			synchronized (this) {
				LOGGER.debug("进入同步代码块，准备睡眠!threadName->{}", Thread.currentThread().getName());
				syncMethod3();
				Thread.sleep(5 * 1000);
				LOGGER.debug("结束同步代码块,结束睡眠!,threadName->{}", Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步方法
	 */
	public synchronized void syncMethod2() {
		try {
			LOGGER.debug("进入同步方法syncMethod2，准备睡眠!threadName->{}", Thread.currentThread().getName());
			syncMethod3();
			Thread.sleep(5 * 1000);
			LOGGER.debug("结束同步方法syncMethod2,结束睡眠!,threadName->{}", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步方法
	 */
	public synchronized void syncMethod3() {
		LOGGER.debug("同步方法syncMethod3，threadName->{}", Thread.currentThread().getName());
	}

	public void syncMethod4() {
		LOGGER.debug("普通方法syncMethod4，threadName->{}", Thread.currentThread().getName());
	}
}