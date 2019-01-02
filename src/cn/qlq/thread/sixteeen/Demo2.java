package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

	public static void main(String[] args) {
		LOGGER.info("main线程组，名字为:{}", Thread.currentThread().getThreadGroup().getName());

		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		ThreadGroup threadGroup2 = new ThreadGroup(threadGroup, "subMain");
		new Thread(threadGroup2, new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threadname ->{},threadGroup ->{}", Thread.currentThread().getName(),
						Thread.currentThread().getThreadGroup().getName());
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadGroups);
		LOGGER.info("main线程有{}线程组，名字为:{}", threadGroups.length, threadGroups[0].getName());

		Thread[] threads = new Thread[threadGroups[0].activeCount()];
		threadGroups[0].enumerate(threads);
		LOGGER.info("" + threads[0].getName());
	}
}