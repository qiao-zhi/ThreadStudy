package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 研究静态方法currentThread
 * 
 * @author Administrator
 *
 */
public class CurrentThreadMethodDemo {

	private static final Logger log = LoggerFactory.getLogger(CurrentThreadMethodDemo.class);

	public static void main(String[] args) {
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				Thread currentThread = Thread.currentThread();
				log.debug("currentThread -> {}", currentThread);
				log.debug("currentThreadName -> {}", currentThread.getName());
			}
		};

		Thread thread = new Thread(r1, "test");
		thread.setPriority(2);
		thread.start();
	}
}
