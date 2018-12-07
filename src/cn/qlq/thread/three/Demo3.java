package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo3.class);

	public static void main(String[] args) {
		Thread.currentThread().interrupt();
		// 发出中断线程信号
		log.debug("是否停止1？{}", Thread.interrupted());
		log.debug("是否停止2？{}", Thread.interrupted());
	}
}
