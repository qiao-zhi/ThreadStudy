package cn.qlq.thread.two;

public class OtherMethod2 {
	public static void main(String[] args) {
		// 构造一个线程并启动，取名为"my-thread"
		Thread myThread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "my-thread");
		myThread.start();

		// 返回一个活线程数量
		int activeCount = Thread.activeCount();
		System.out.println(activeCount);
		Thread[] ts = new Thread[activeCount];
		Thread.enumerate(ts);
		for (Thread t : ts) {
			System.out.println("从主线程中找到名为my-thread的线程，线程名称为：" + t.getName() + ", 状态为: " + t.getState());
		}
	}
}
