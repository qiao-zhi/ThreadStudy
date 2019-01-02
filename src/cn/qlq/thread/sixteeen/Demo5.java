package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo5 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

	public static void main(String[] args) {
		LOGGER.info("线程组名字为:{}，父线程组名称:{}", Thread.currentThread().getThreadGroup().getName(),
				Thread.currentThread().getThreadGroup().getParent().getName());
		LOGGER.info("线程组中活动的线程数量为:{}", Thread.currentThread().getThreadGroup().activeCount());
		LOGGER.info("线程组中活动的线程组数量为:{}---加之前", Thread.currentThread().getThreadGroup().activeGroupCount());
		ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(), "main2");
		LOGGER.info("线程组中活动的线程组数量为:{}---加之后", Thread.currentThread().getThreadGroup().activeGroupCount());

		// 遍历线程组
		ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
		Thread.currentThread().getThreadGroup().getParent().enumerate(threadGroups);
		for (ThreadGroup threadGroup2 : threadGroups) {
			LOGGER.info("" + threadGroup2.getName());
		}
	}
}