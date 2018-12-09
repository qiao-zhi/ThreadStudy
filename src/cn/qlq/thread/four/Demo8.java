package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo8 extends Thread {

	public static void main(String[] args) {
		final SyncObj9 syncObj9 = new SyncObj9();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj9.syncMethod2();
			}
		}, "A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj9.syncMethod2();
			}
		}, "B").start();
	}
}

class SyncObj9 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj9.class);

	public void syncMethod2() {
		LOGGER.debug("SyncObj9 syncMethod2 start,threadName->{}", Thread.currentThread().getName());
		try {
			Thread.sleep(5 * 1000);
			synchronized (this) {
				LOGGER.debug("进入同步代码块，准备睡眠!threadName->{}", Thread.currentThread().getName());
				Thread.sleep(5 * 1000);
				LOGGER.debug("结束同步代码块,结束睡眠!,threadName->{}", Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.debug("SyncObj9 syncMethod2 end,threadName->{}", Thread.currentThread().getName());
	}
}