package cn.qlq.thread.one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(SubThread.class);

	@Override
	public void run() {
		log.debug("subThread run,threadname->{}", Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		SubThread subThread = new SubThread();
		subThread.start();
		log.debug("运行结束,threadname->{}", Thread.currentThread().getName());
	}
}
