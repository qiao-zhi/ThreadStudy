package cn.qlq.thread.fifteen;

/**
 * 静态内置类实现单例模式
 * 
 * @author Administrator
 *
 */
public class Singleton_3 {

	private static class Singleton_3Handle {
		private static final Singleton_3 instance = new Singleton_3();
	}

	private Singleton_3() {
	}

	public static Singleton_3 getInstance() {
		return Singleton_3Handle.instance;
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
