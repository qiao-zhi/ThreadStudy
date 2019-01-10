package cn.qlq.thread.twenty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo5 {
	private static final ExecutorService batchTaskPool = Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			batchTaskPool.execute(new MyRunnable());
		}
	}
}
