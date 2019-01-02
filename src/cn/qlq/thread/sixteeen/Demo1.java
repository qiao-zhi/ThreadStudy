package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo1 implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);

	@Override
	public void run() {
		LOGGER.info("threadname ->{},threadGroup ->{}", Thread.currentThread().getName(),
				Thread.currentThread().getThreadGroup().getName());
	}

	public static void main(String[] args) {
		ThreadGroup threadGroup = new ThreadGroup("g1");
		new Thread(threadGroup, new Demo1()).start();
		new Thread(threadGroup, new Demo1()).start();
	}
}