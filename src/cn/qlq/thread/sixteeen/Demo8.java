package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo8 extends Thread {

	@Override
	public void run() {
		while (true) {

		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo8.class);

	public static void main(String[] args) throws InterruptedException {
		Thread t0 = new Thread(new Demo8());
		t0.start();

		// 向main组内增加main2组和一个线程对象
		ThreadGroup threadGroup2 = new ThreadGroup(Thread.currentThread().getThreadGroup(), "main2");
		Thread t1 = new Thread(threadGroup2, new Demo8());
		t1.start();

		LOGGER.info("==========={}=======", Thread.currentThread().getThreadGroup().activeCount());

		// 分配空间，不一定全部用完
		Thread thread1[] = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread thread2[] = new Thread[Thread.currentThread().getThreadGroup().activeCount()];

		// 传入true是递归获取其子孙线程
		Thread.currentThread().getThreadGroup().enumerate(thread1, true);
		for (Thread t : thread1) {
			LOGGER.info("threadName->{}", t.getName());
		}

		// 传入false是只获取直属的线程
		LOGGER.info("==========={}=======", Thread.currentThread().getThreadGroup().activeCount());
		Thread.currentThread().getThreadGroup().enumerate(thread2, false);
		for (int i = 0; i < thread2.length; i++) {
			if (thread2[i] != null) {
				LOGGER.info("threadName->{}", thread2[i].getName());
			} else {
				LOGGER.info("{}为null", i);
			}
		}

	}
}