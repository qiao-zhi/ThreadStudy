package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo1 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo1.class);

	@Override
	public void run() {
		for (int i = 0; i < 50;) {
			log.debug("i->{}", i++);
		}
	}

	public static void main(String[] args) {
		try {
			Demo1 demo1 = new Demo1();
			demo1.start();
			Thread.sleep(200);
			// 发出中断线程信号
			demo1.interrupt();
		} catch (InterruptedException e) {
			log.error("main catch ", e);
		}

	}
}
