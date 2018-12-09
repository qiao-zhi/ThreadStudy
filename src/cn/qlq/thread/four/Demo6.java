package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo6 extends Thread {

	public static void main(String[] args) {
		final SyncObj6 syncObj6 = new SyncObj6();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj6.syncMethod2();
			}
		}, "A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj6.syncMethod2();
			}
		}, "B").start();
	}
}

class SyncObj6 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj6.class);

	public synchronized void syncMethod2() {
		if ("A".equals(Thread.currentThread().getName())) {
			LOGGER.debug("syncMethod2 ,threadName->{}", Thread.currentThread().getName());
			int i = 1 / 0;
		} else {
			LOGGER.debug("syncMethod2 ,threadName->{}", Thread.currentThread().getName());
		}
		LOGGER.debug("syncMethod2 end ,threadName->{}", Thread.currentThread().getName());
	}

}
