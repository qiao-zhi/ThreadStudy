package cn.qlq.thread.four;

public class Demo14 {

	public static void main(String[] args) {
		final Integer lock1 = 1;
		final Integer lock2 = 2;
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				synchronized (lock1) {
					try {
						System.out.println("lock1-----");
						Thread.sleep(2 * 1000);
						synchronized (lock2) {
							System.out.println("lock1-----lock2");
						}
					} catch (InterruptedException e) {
					}
				}
			}
		};
		new Thread(r1).start();

		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				synchronized (lock2) {
					try {
						System.out.println("lock2-----");
						Thread.sleep(2 * 1000);
						synchronized (lock1) {
							System.out.println("lock2-----lock1");
						}
					} catch (InterruptedException e) {
					}
				}
			}
		};
		new Thread(r2).start();
	}
}