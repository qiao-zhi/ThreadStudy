package cn.qlq.thread.fifteen;

/**
 * 延迟加载==饱汉模式
 * 
 * @author Administrator
 *
 */
public class Singleton_2 {

	private volatile static Singleton_2 instance;

	private Singleton_2() {
	}

	public static Singleton_2 getInstance() {
		try {
			if (instance == null) {
				synchronized (Singleton_2.class) {
					// 模拟处理其他事情
					Thread.sleep(3 * 1000);
					if (instance == null) {
						instance = new Singleton_2();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(Singleton_2.getInstance().hashCode());
			}
		};
		new Thread(runnable).start();
		new Thread(runnable).start();
		new Thread(runnable).start();
	}
}
