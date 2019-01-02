package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo6 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo6.class);

	public static void main(String[] args) throws InterruptedException {

		ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(), "main2");
		LOGGER.info("线程组中活动的线程组数量为:{}---加之后", Thread.currentThread().getThreadGroup().activeGroupCount());
		for (int i = 0; i < 5; i++) {
			new Thread(threadGroup, new Runnable() {
				@Override
				public void run() {
					LOGGER.info("threadName->{}准备死循环", Thread.currentThread().getName());
					while (!Thread.currentThread().isInterrupted()) {

					}
					LOGGER.info("threadName->{}结束死循环", Thread.currentThread().getName());
				}
			}).start();
		}

		Thread.sleep(2 * 1000);
		// 向线程组发出终端信号
		threadGroup.interrupt();
	}
}