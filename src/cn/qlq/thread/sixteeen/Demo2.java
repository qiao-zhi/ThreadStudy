package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;

public class Demo2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

	public static void main(String[] args) {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		ThreadGroup threadGroup2 = new ThreadGroup(threadGroup, "subMain");
		new Thread(threadGroup2, new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threadname ->{},threadGroup ->{}", Thread.currentThread().getName(),
						Thread.currentThread().getThreadGroup().getName());
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