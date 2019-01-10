package cn.qlq.thread.twenty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo6 {
	private static final ScheduledExecutorService batchTaskPool = Executors.newScheduledThreadPool(2);

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			// 第一次执行是在3s后执行(延迟任务)
			batchTaskPool.schedule(new MyRunnable(), 3, TimeUnit.SECONDS);
			// 第一个参数是需要执行的任务，第二个参数是第一次的延迟时间，第三个参数是两次执行的时间间隔，第四个参数是时间的单位
			batchTaskPool.scheduleAtFixedRate(new MyRunnable(), 3, 7, TimeUnit.SECONDS);
			// 第一个参数是需要执行的任务，第二个参数是第一次的延迟时间，第三个参数是两次执行的时间间隔，第四个参数是时间的单位
			batchTaskPool.scheduleWithFixedDelay(new MyRunnable(), 3, 5, TimeUnit.SECONDS);
		}
	}
}
