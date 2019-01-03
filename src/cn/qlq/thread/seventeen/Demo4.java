package cn.qlq.thread.seventeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo4 extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo4.class);
	private Integer num;

	public Demo4(Integer num, ThreadGroup threadGroup) {
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
		MyThreadGroup2 threadGroup = new MyThreadGroup2(new ThreadGroup("myGroup"));
		Thread t1 = new Demo4(0, threadGroup);
		t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				LOGGER.error("线程中报错，threadName->{}", t.getName(), e);
			}
		});

		t1.start();
		Thread t2 = new Demo4(0, threadGroup);
		t2.start();
	}
}

class MyThreadGroup2 extends ThreadGroup {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyThreadGroup2.class);

	public MyThreadGroup2(ThreadGroup threadGroup) {
		super(threadGroup, threadGroup.getName());
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOGGER.error("UncaughtException , threadName -> {},ThreadGroupName -> {}", t.getName(),
				t.getThreadGroup().getName(), e);
		super.uncaughtException(t, e);
	}
}
