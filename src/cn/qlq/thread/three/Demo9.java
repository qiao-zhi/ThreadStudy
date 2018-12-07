package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * stop暴力停止
 * 
 * @author Administrator
 *
 */
public class Demo9 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo9.class);

	@Override
	public void run() {
		int i = 0;
		while (true) {
			log.debug("{}", i++);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Demo9 demo9 = new Demo9();
		demo9.start();
		// 暴力停止
		try {
			demo9.stop();
		} catch (Throwable e) {
			log.error("stop error", e);
		}
		log.debug("end");
	}
}
