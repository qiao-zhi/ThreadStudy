package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo5 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo5.class);

	@Override
	public void run() {
		for (int i = 0; i < 5000000;) {
		}
	}

	public static void main(String[] args) {
		try {
			Demo5 demo2 = new Demo5();
			demo2.start();
			Thread.sleep(1000);
			// 发出中断线程信号
			log.debug("是否停止0？{}", demo2.isInterrupted());
			demo2.interrupt();
			log.debug("是否停止1？{}", demo2.isInterrupted());
			log.debug("是否停止2？{}", demo2.isInterrupted());
		} catch (InterruptedException e) {
			log.error("main catch ", e);
		}
		log.debug("end");
	}
}
