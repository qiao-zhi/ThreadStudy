package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo4 extends Thread {

	public static void main(String[] args) {
		final SyncObj3 syncObj2 = new SyncObj3();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj2.syncMethod1();
			}
		}, "A").start();
	}
}

class SyncObj3 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj2.class);

	public synchronized void syncMethod1() {
		LOGGER.debug("SyncObj2 syncMethod1 start!!!" + Thread.currentThread().getName());
		syncMethod2();
	}

	public synchronized void syncMethod2() {
		LOGGER.debug("SyncObj2 syncMethod2 start!!!" + Thread.currentThread().getName());
	}

}