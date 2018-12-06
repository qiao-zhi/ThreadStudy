package cn.qlq.thread.two;

public class ThreadSleep2SeeJVMThread {
	public static void main(String[] args) {
		// 构造一个线程并启动，取名为"my-thread"
		Thread myThread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(100 * 10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "my-thread");
		myThread.start();
		System.out.println("myThread id ->" + myThread.getId());

		try {
			Thread.sleep(500 * 10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
