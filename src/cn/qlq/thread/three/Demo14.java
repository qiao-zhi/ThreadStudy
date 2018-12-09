package cn.qlq.thread.three;

public class Demo14 extends Thread {

	int i;

	@Override
	public void run() {
		while (true) {
			System.out.println(i++);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Demo14 demo14 = new Demo14();
		demo14.start();

		Thread.sleep(100);
		demo14.suspend();
		System.out.println("main end");
	}
}