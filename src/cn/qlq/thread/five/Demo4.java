package cn.qlq.thread.five;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo4 {
	public static void main(String[] args) throws InterruptedException {
		Runnable sync4 = new Sync4();
		for (int i = 0; i < 50; i++) {
			new Thread(sync4, "" + i).start();
		}
	}
}

class Sync4 implements Runnable {
	volatile private AtomicInteger count = new AtomicInteger(0);

	public void run() {
		System.out.println(count.getAndIncrement() + "\tthreadName->" + Thread.currentThread().getName());
	}
}
