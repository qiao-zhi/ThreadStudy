package cn.qlq.thread.fourteen;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo5 extends TimerTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);
	private String name;

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("start ");
		Timer timer = new Timer();
		Demo5 task = new Demo5("t1");
//		timer.scheduleAtFixedRate(task, 2 * 1000, 10 * 1000);// 延时相对于任务的开始时间进行延迟
//		timer.scheduleAtFixedRate(task, new Date(), 10 * 1000);// 没有延时相对于任务的开始时间进行延迟
		timer.schedule(task, 10 * 1000);// 没有延时相对于任务的开始时间进行延迟
		LOGGER.info("end ");
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.info("threadName -> {},value -> {},taskName->{}", Thread.currentThread().getName(), i + "", name);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Demo5(String name) {
		super();
		this.name = name;
	}
}
