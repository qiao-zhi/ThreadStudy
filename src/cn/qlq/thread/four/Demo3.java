package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3 extends Thread {

	public static void main(String[] args) {
		final SyncObj2 syncObj2 = new SyncObj2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj2.syncMethod();
			}
		}, "A").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj2.asyncMethod();
			}
		}, "B").start();
	}
}

class SyncObj2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj2.class);

	public synchronized void syncMethod() {
		LOGGER.debug("SyncObj2 syncMethod start!!!" + Thread.currentThread().getName());
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.debug("SyncObj2 syncMethod end!!!" + Thread.currentThread().getName());
	}

	public synchronized void asyncMethod() {
		LOGGER.debug("SyncObj2 asyncMethod start!!!" + Thread.currentThread().getName());
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.debug("SyncObj2 asyncMethod end!!!" + Thread.currentThread().getName());
	}
}