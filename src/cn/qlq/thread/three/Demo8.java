package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 先睡眠后中断也会进入异常
 * 
 * @author Administrator
 *
 */
public class Demo8 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo4.class);

	public static void main(String[] args) {
		// 发出中断信号
		Thread.currentThread().interrupt();
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			log.debug("interruptedException", e);
		}
	}
}
