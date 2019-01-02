package cn.qlq.thread.fifteen;

/**
 * 立即加载==饿汉模式
 * 
 * @author Administrator
 *
 */
public class Singleton_1 {

	private static Singleton_1 instance = new Singleton_1();

	private Singleton_1() {
	}

	public static Singleton_1 getInstance() {
		// 缺点是不能有其他实例变量，因为此方法没有同步所以有可能出现线程非安全问题
		return instance;
	}
}
