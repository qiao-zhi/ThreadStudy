package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo7 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo7.class);

	public static void main(String[] args) throws InterruptedException {
		// 向main组内增加main2组，main2中增加main22，main22中增加main222
		ThreadGroup threadGroup2 = new ThreadGroup(Thread.currentThread().getThreadGroup(), "main2");
		ThreadGroup threadGroup22 = new ThreadGroup(threadGroup2, "main22");
		ThreadGroup threadGroup222 = new ThreadGroup(threadGroup22, "main222");

		LOGGER.info("==========={}=======", Thread.currentThread().getThreadGroup().activeGroupCount());

		// 分配空间，不一定全部用完
		ThreadGroup threadGroups1[] = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
		ThreadGroup threadGroups2[] = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];

		// 传入true是递归获取其子孙组
		Thread.currentThread().getThreadGroup().enumerate(threadGroups1, true);
		for (ThreadGroup t : threadGroups1) {
			LOGGER.info("threadGroupName->{}", t.getName());
		}

		// 传入false是只获取直属的线程组
		LOGGER.info("==========={}=======", Thread.currentThread().getThreadGroup().activeGroupCount());
		Thread.currentThread().getThreadGroup().enumerate(threadGroups2, false);
		for (int i = 0; i < threadGroups2.length; i++) {
			if (threadGroups2[i] != null) {
				LOGGER.info("threadGroupName->{}", threadGroups2[i].getName());
			} else {
				LOGGER.info("{}为null", i);
			}
		}

	}
}