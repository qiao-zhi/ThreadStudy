package cn.qlq.thread.fourteen;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2 extends TimerTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);
	private String name;

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("start ");
		Timer timer = new Timer();
		Demo2 task = new Demo2("t1");
		Date runtime = new Date(System.currentTimeMillis());
		timer.schedule(task, runtime, 10 * 1000);
		LOGGER.info("end ");
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			LOGGER.info("threadName -> {},value -> {},taskName->{}", Thread.currentThread().getName(), i + "", name);
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Demo2(String name) {
		super();
		this.name = name;
	}
}
