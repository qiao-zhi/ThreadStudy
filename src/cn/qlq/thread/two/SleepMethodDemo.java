package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 研究sleep方法
 * 
 * @author Administrator
 *
 */
public class SleepMethodDemo {

	private static final Logger log = LoggerFactory.getLogger(SleepMethodDemo.class);

	public static void main(String[] args) throws InterruptedException {
		Thread r1 = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.debug("over sleep,{}", System.currentTimeMillis());
			}
		};
		log.debug("begain->{}", System.currentTimeMillis());
		r1.start();
		Thread.sleep(1 * 1000);
		log.debug("end---r1 isAlive->{},r1.state->{}", r1.isAlive(), r1.getState());
		log.debug("end->{}", System.currentTimeMillis());
	}
}
