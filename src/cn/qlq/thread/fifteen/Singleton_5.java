package cn.qlq.thread.fifteen;

/**
 * 静态代码块实现单例模式
 * 
 * @author Administrator
 *
 */
public class Singleton_5 {

	private static Singleton_5 instance = null;

	private Singleton_5() {

	}

	static {
		instance = new Singleton_5();
	}

	public static Singleton_5 getInstance() {
		return instance;
	}

}
