package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo10 extends Thread {

	public static void main(String[] args) throws InterruptedException {
		// 同步对象
		final SyncObj11 syncObj11 = new SyncObj11();
		final SyncObj11 syncObj12 = new SyncObj11();
		final Integer integer = 1;
		// 访问同步代码块占用锁
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj11.syncMethod1(integer);
			}
		}, "A").start();
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncObj12.syncMethod1(integer);
			}
		}, "B").start();
	}
}

class SyncObj11 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SyncObj11.class);

	/**
	 * 同步代码块
	 */
	public void syncMethod1(Integer x) {
		try {
			synchronized (x) {
				LOGGER.debug("进入同步代码块，准备睡眠!threadName->{}", Thread.currentThread().getName());
				Thread.sleep(5 * 1000);
				LOGGER.debug("结束同步代码块,结束睡眠!,threadName->{}", Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}