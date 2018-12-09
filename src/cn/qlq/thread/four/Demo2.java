package cn.qlq.thread.four;

public class Demo2 extends Thread {
	private SyncObj1 syncObj1;

	public Demo2(SyncObj1 syncObj1) {
		this.syncObj1 = syncObj1;
	}

	@Override
	public void run() {
		syncObj1.syncMethod();
	}

	public static void main(String[] args) {
		Demo2 demo2 = new Demo2(new SyncObj1());
		Demo2 demo3 = new Demo2(new SyncObj1());
		demo2.start();
		demo3.start();
	}
}

class SyncObj1 {
	public synchronized void syncMethod() {
		for (int i = 0; i < 5; i++) {
			System.out.println("threadName->" + Thread.currentThread().getName());
		}
	}
}