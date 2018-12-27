package cn.qlq.thread.fourteen;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo1 extends TimerTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);
	private String name;

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("start ");
		Timer timer = new Timer();
		Demo1 task = new Demo1("t1");
		Demo1 task2 = new Demo1("t2");
		Date runtime = new Date(System.currentTimeMillis() - 5000);
		timer.schedule(task, runtime);
		timer.schedule(task2, runtime);
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

	public Demo1(String name) {
		super();
		this.name = name;
	}
}
