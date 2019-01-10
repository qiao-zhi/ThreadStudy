package cn.qlq.thread.twenty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(MyRunnable.class);

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			log.info("threadName -> {},i->{} ", Thread.currentThread().getName(), i);
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}