package cn.qlq.thread.fifteen;

/**
 * 枚举实现单例模式
 * 
 * @author Administrator
 *
 */
public class Singleton_7 {
	public enum Singleton_7Handle {

		instance;

		private Singleton_7 singleton_7;

		private Singleton_7Handle() {
			singleton_7 = new Singleton_7();
			System.out.println("调用构造方法");
		}

		public Singleton_7 getSingleton_7() {
			return singleton_7;
		}

		public Singleton_7 getInstance() {
			return instance.getSingleton_7();
		}
	}

	public static void main(String[] args) {
		System.out.println(Singleton_7Handle.instance.getInstance());
	}

}
