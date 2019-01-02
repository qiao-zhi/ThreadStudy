package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);

	public static void main(String[] args) {
		LOGGER.info("main线程组，名字为:{}", Thread.currentThread().getThreadGroup().getName());

		new Thread(new Runnable() {
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

		ThreadGroup threadGroup2 = new ThreadGroup("subMain");
		LOGGER.info("threadGroup2线程组的父线程组是->{}", threadGroup2.getParent().getName());
	}
}