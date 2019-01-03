package cn.qlq.thread.seventeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3 extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);
	private Integer num;

	public Demo3(Integer num, ThreadGroup threadGroup) {
		super(threadGroup, num + "" + Math.random());
		this.num = num;
	}

	@Override
	public void run() {
		int i = 1 / num;
		while (!this.isInterrupted()) {
			LOGGER.info("num = {}", num++);
		}
	}

	public static void main(String[] args) {
		MyThreadGroup threadGroup = new MyThreadGroup(new ThreadGroup("myGroup"));
		Thread t1 = new Demo3(0, threadGroup);
		Thread t2 = new Demo3(0, threadGroup);
		Thread t3 = new Demo3(1, threadGroup);
		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThreadGroup extends ThreadGroup {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyThreadGroup.class);

	public MyThreadGroup(ThreadGroup threadGroup) {
		super(threadGroup, threadGroup.getName());
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOGGER.error("UncaughtException , threadName -> {},ThreadGroupName -> {},向组发出中断信号", t.getName(),
				t.getThreadGroup().getName(), e);
		this.interrupt();
	}
}
