package cn.qlq.thread.fifteen;

/**
 * 枚举实现单例模式
 * 
 * @author Administrator
 *
 */
public enum Singleton_6 {

	instance;

	private Singleton_6() {
		System.out.println("调用构造方法");
	}

	public Singleton_6 getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(Singleton_6.instance);
	}
}
