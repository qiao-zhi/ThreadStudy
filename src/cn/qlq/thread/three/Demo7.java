package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 沉睡中中断会进捕捉到中断异常
 * 
 * @author Administrator
 *
 */
public class Demo7 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo4.class);

	@Override
	public void run() {
		super.run();
		try {
			Thread.sleep(50 * 1000);
		} catch (InterruptedException e) {
			log.error("进入catch方法");
			log.info("demo7.isInterrupted  2-> {}", this.isInterrupted());
		}
	}

	public static void main(String[] args) {
		Demo7 demo7 = new Demo7();
		demo7.start();
		// 发出中断信号
		demo7.interrupt();
		log.info("demo7.isInterrupted  1-> {}", demo7.isInterrupted());
		log.debug("end");
	}
}
