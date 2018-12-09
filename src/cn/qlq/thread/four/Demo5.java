package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo5 extends Thread {

	public static void main(String[] args) {
		final SyncObj5 syncObj5 = new SyncObj5();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj5.syncMethod1();
			}
		}, "A").start();
	}
}

class SyncObj4 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj4.class);

	public synchronized void syncMethod2() {
		LOGGER.debug("SyncObj2 syncMethod2 start!!!" + Thread.currentThread().getName());
	}

}

class SyncObj5 extends SyncObj4 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj5.class);

	public synchronized void syncMethod1() {
		LOGGER.debug("SyncObj2 syncMethod2 start!!!" + Thread.currentThread().getName());
		syncMethod2();
	}

}