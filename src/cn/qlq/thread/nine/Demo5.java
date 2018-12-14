package cn.qlq.thread.nine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;

/**
 * sleep(long)不会释放锁
 * 
 * @author Administrator
 *
 */
public class Demo5 extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("main start");
		final Demo5 demo4 = new Demo5();

		// 启动demo4线程并且占用锁之后调用join(long)方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (demo4) {
						LOGGER.info("进入同步代码块，threadName ->{} 占有 demo4 的锁", Thread.currentThread().getName());
						demo4.start();
						demo4.sleep(4 * 1000);
						LOGGER.info("退出同步代码块，threadName ->{}", Thread.currentThread().getName());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "threadA").start();

		// 休眠2秒钟，调用对象的同步方法
		Thread.currentThread().sleep(2 * 1000);
		demo4.test2();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void test2() {
		LOGGER.info("进入test2方法，占有锁，threadname->{}", currentThread().getName());
	}
}