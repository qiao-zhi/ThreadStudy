package cn.qlq.thread.four;

public class Demo17 {
	
	// 对class字节码对象加锁
	public static void test0() {
		try {
			synchronized (Demo17.class) {
				Thread.sleep(2 * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 对class字节码对象加锁
	public static synchronized void test1() {
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 对当前对象加锁
	public synchronized void test2() {
		try {
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 对当前对象加锁
	public void test3() {
		try {
			synchronized (this) {
				Thread.sleep(2 * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
