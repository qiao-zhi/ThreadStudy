package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 研究getId
 * 
 * @author Administrator
 *
 */
public class GetIdMethodDemo extends Thread {

	private static final Logger log = LoggerFactory.getLogger(GetIdMethodDemo.class);

	@Override
	public void run() {
		log.debug("threadName->{},getId->{}", Thread.currentThread().getName(), Thread.currentThread().getId());
	}

	public static void main(String[] args) throws InterruptedException {
		log.debug("threadName->{},getId->{}", Thread.currentThread().getName(), Thread.currentThread().getId());
		for (int i = 0; i < 10; i++) {
			GetIdMethodDemo t1 = new GetIdMethodDemo();
			t1.start();
		}
	}
}
