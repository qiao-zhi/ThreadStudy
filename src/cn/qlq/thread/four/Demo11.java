package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo11 extends Thread {

	public static void main(String[] args) throws InterruptedException {
		// 同步对象
		final SyncObj12 syncObj11 = new SyncObj12();
		final SyncObj12 syncObj12 = new SyncObj12();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj11.syncMethod1();
			}
		}, "A").start();
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj12.syncMethod1();
			}
		}, "B").start();
		// 访问静态方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				SyncObj12.syncMethod1();
			}
		}, "C").start();
	}
}

class SyncObj12 {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj12.class);

	/**
	 * 同步代码块
	 */
	public static synchronized void syncMethod1() {
		LOGGER.debug("进入同步代码块，准备睡眠!threadName->{}", Thread.currentThread().getName());
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.debug("结束同步代码块,结束睡眠!,threadName->{}", Thread.currentThread().getName());
	}
}