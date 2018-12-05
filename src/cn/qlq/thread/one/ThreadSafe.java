package cn.qlq.thread.one;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafe extends Thread {

	private AtomicInteger i = new AtomicInteger(0);// 默认值为0

	@Override
	public void run() {
		System.out.println("i=" + (i.getAndIncrement()) + ",threadName=" + Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		ThreadSafe run = new ThreadSafe();
		// 开启是个线程进行计算
		for (int i = 0; i < 10; i++) {
			Thread t1 = new Thread(run);
			t1.start();
		}
	}
}
