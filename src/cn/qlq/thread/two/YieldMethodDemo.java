package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YieldMethodDemo extends Thread {
	private static final Logger log = LoggerFactory.getLogger(YieldMethodDemo.class);

	@Override
	public void run() {
		long begainTime = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 5000000; i++) {
//			Thread.yield();
			count = count + (i + 1);
		}
		long endTime = System.currentTimeMillis();
		log.debug("用时:{}毫秒", endTime - begainTime);
	}

	public static void main(String[] args) {
		YieldMethodDemo tDemo = new YieldMethodDemo();
		tDemo.start();
	}
}
