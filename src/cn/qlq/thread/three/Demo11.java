package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * return + interrupt结束线程
 * 
 * @author Administrator
 *
 */
public class Demo11 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo11.class);

	@Override
	public void run() {
		while (true) {
			if (this.isInterrupted()) {
				log.debug("run收到终止信号,终止了!");
				return;
			}
			log.debug("sss");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 暴力停止
		Demo11 demo11 = new Demo11();
		demo11.start();
		Thread.sleep(100);
		demo11.interrupt();
	}
}
