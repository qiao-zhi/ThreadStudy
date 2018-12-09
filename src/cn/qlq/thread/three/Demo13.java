package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo13 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo13.class);
	private SyncObj2 syncObj2;

	public Demo13(String name, SyncObj2 syncObj2) {
		this.setName(name);
		this.syncObj2 = syncObj2;
	}

	@Override
	public void run() {
		syncObj2.testSync();
	}

	public static void main(String[] args) throws InterruptedException {
		SyncObj2 syncObj2 = new SyncObj2();
		Demo13 demo131 = new Demo13("a", syncObj2);
		demo131.start();
		// 休眠是为了使上面线程占用锁
		Thread.sleep(1000);

		Demo13 demo132 = new Demo13("b", syncObj2);
		demo132.start();

		// 打印状态
		System.out.println("demo131.getState()->" + demo131.getState());
		System.out.println("demo132.getState()->" + demo132.getState());
	}
}

class SyncObj2 {
	public synchronized void testSync() {
		System.out.println("进入testSync同步方法");
		if ("a".equals(Thread.currentThread().getName())) {
			System.out.println("此线程将挂起，永远不会释放锁，其他线程无法调用此方法");
			Thread.currentThread().suspend();
		}
		System.out.println("退出testSync同步方法");
	}
}
