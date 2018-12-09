package cn.qlq.thread.four;

public class Demo1 extends Thread {
	private int i;// 默认值为0

	@Override
	public void run() {
		System.out.println("i=" + (i++) + ",threadName=" + Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		Demo1 run = new Demo1();
		// 开启十个线程进行计算
		for (int i = 0; i < 10; i++) {
			Thread t1 = new Thread(run);
			t1.start();
		}
	}
}