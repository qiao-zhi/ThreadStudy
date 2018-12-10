package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo13 extends Thread {

	public static void main(String[] args) throws InterruptedException {

		// 同步对象
		final SyncObj14 syncObj14 = new SyncObj14();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj14.syncMethod1(new Object());
			}
		}, "A").start();
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj14.syncMethod1(new Object());
			}
		}, "B").start();
	}
}

class SyncObj14 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj14.class);

	/**
	 * 同步代码块
	 */
	public static void syncMethod1(Object param) {
		synchronized (param) {
			LOGGER.debug("进入同步代码块，准备睡眠!threadName->{}", Thread.currentThread().getName());
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.debug("结束同步代码块,结束睡眠!,threadName->{}", Thread.currentThread().getName());
		}
	}
}