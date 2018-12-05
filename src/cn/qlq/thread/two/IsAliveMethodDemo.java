package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 研究isAlive方法
 * 
 * @author Administrator
 *
 */
public class IsAliveMethodDemo {

	private static final Logger log = LoggerFactory.getLogger(IsAliveMethodDemo.class);

	public static void main(String[] args) throws InterruptedException {
		Thread r1 = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1 * 10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.debug("run isAlive->{}", this.isAlive());// F
			}
		};
		log.debug("begain---r1 isAlive->{}", r1.isAlive());// T
		r1.start();
		log.debug("end---r1 isAlive->{}", r1.isAlive());// T
		log.debug("finish");
	}
}
