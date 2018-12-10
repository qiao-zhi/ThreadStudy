package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo12 extends Thread {

	public static void main(String[] args) throws InterruptedException {
		// 同步对象
		final SyncObj13 syncObj131 = new SyncObj13();
		final SyncObj13 syncObj132 = new SyncObj13();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj131.syncMethod1();
			}
		}, "A").start();
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj132.syncMethod1();
			}
		}, "B").start();
		// 访问静态方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				SyncObj13.syncMethod1();
			}
		}, "C").start();
	}
}

class SyncObj13 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj13.class);

	/**
	 * 同步代码块
	 */
	public static void syncMethod1() {
		synchronized (SyncObj13.class) {
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