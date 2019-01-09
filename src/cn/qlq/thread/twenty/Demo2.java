package cn.qlq.thread.twenty;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
	private static volatile AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(atomicInteger.incrementAndGet());
				}
			});
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println(atomicInteger);
	}
}
