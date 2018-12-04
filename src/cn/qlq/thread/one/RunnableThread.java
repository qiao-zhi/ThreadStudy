package cn.qlq.thread.one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableThread implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(RunnableThread.class);

	@Override
	public void run() {
		log.debug("runnableThread run,threadname->{}", Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		RunnableThread runnableThread1 = new RunnableThread();
		Thread t1 = new Thread(runnableThread1);
		Thread t2 = new Thread(runnableThread1, "t2");
		t1.start();
		t2.start();
		log.debug("运行结束,threadname->{}", Thread.currentThread().getName());
	}
}
