package cn.qlq.thread.five;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo5 {
	public static void main(String[] args) throws InterruptedException {
		Sync5 sync4 = new Sync5();
		Thread[] threads = new Thread[5];
		for (int i = 0; i < 5; i++) {
			threads[i] = new Thread(sync4);
		}
		for (int i = 0; i < 5; i++) {
			threads[i].start();
		}
	}
}

class Sync5 implements Runnable {
	private AtomicInteger count = new AtomicInteger(0);

	public synchronized void run() {
		System.out.println("\tthreadName->" + Thread.currentThread().getName() + ",加了100后的值是" + count.addAndGet(100));
		count.addAndGet(1);
	}
}
