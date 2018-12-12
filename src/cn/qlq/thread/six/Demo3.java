package cn.qlq.thread.six;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * wait/notify的基本使用
 * 
 * @author Administrator
 *
 */
public class Demo3 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);

	public static void main(String[] args) throws InterruptedException {
		final Object object = new Object();
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (object) {
					try {
						LOGGER.debug("进入同步代码块，准备wait(),threadName->{}", Thread.currentThread().getName());
						Thread.sleep(1 * 1000);
						object.wait();
						LOGGER.debug("退出同步代码块，wait()结束,threadName->{}", Thread.currentThread().getName());
					} catch (InterruptedException e) {
						LOGGER.error("InterruptedException error");
					}
				}
			}
		}, "A");
		threadA.start();

		// 开启一个线程占用锁之后唤醒一个线程
		Thread.sleep(1);
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (object) {
					try {
						LOGGER.debug("进入同步代码块，准备wait(),threadName->{}", Thread.currentThread().getName());
						Thread.sleep(1 * 1000);
						object.wait();
						LOGGER.debug("退出同步代码块，wait()结束,threadName->{}", Thread.currentThread().getName());
					} catch (InterruptedException e) {
						LOGGER.error("InterruptedException error");
					}
				}
			}
		}, "B");
		threadB.start();

		// 开启一个线程占用锁之后唤醒一个线程
		Thread.sleep(1);
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (object) {
					try {
						LOGGER.debug("进入同步代码块，准备notify(),threadName->{}", Thread.currentThread().getName());
						Thread.sleep(1 * 1000);
						object.notify();
						// object.notifyAll();
						LOGGER.debug("退出同步代码块，notify()结束,threadName->{}", Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "C");
		threadC.start();

		Thread.sleep(5 * 1000);
		LOGGER.debug("A线程状态->{}", threadA.getState());
		LOGGER.debug("B线程状态->{}", threadB.getState());
		LOGGER.debug("C线程状态->{}", threadC.getState());
	}
}
