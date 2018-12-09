package cn.qlq.thread.two;

public class ThreadStackSize {
	public static void main(String[] args) {
		Thread myThread = new Thread(null, new Runnable() {
			public void run() {
				byte[] tt = new byte[1025 * 1024];
			}
		}, "name", 1024);
		myThread.start();
		System.out.println("myThread id ->" + myThread.getId());
	}
}
