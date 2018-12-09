package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo7 extends Thread {

	public static void main(String[] args) {
		final SyncObj8 syncObj8 = new SyncObj8();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj8.syncMethod2();
			}
		}, "A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj8.syncMethod2();
			}
		}, "B").start();
	}
}

class SyncObj7 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj7.class);

	public synchronized void syncMethod2() {
		LOGGER.debug("SyncObj7 syncMethod2,threadName->{}", Thread.currentThread().getName());
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SyncObj8 extends SyncObj7 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj8.class);

	@Override
	synchronized public void syncMethod2() {
		LOGGER.debug("SyncObj8 syncMethod2,threadName->{}", Thread.currentThread().getName());
		try {
			Thread.sleep(5 * 1000);
			super.syncMethod2();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
