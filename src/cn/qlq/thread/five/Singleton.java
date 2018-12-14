package cn.qlq.thread.five;

/**
 * volatile用作单例的双重检查
 * 
 * @author Administrator
 *
 */
public class Singleton {

	private volatile static Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		// first check
		if (instance == null) {
			synchronized (Singleton.class) {
				// second check
				if (instance == null) {
					instance = new Singleton(); // new
				}
			}
		}
		return instance;
	}

}
