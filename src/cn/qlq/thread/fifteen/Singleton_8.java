package cn.qlq.thread.fifteen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 静态代码块实现单例模式
 * 
 * @author Administrator
 *
 */
public class Singleton_8 {

	private static Singleton_8 instance = null;

	private Singleton_8() {

	}

	static {
		instance = new Singleton_8();
	}

	public static Singleton_8 getInstance() {
		return instance;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(Singleton_8.getInstance().hashCode());
		Class clazz = Singleton_8.class;
		Constructor declaredConstructor = clazz.getDeclaredConstructor();
		declaredConstructor.setAccessible(true);
		Singleton_8 newInstance = (Singleton_8) declaredConstructor.newInstance();
		Singleton_8 newInstance2 = (Singleton_8) declaredConstructor.newInstance();
		System.out.println(newInstance.hashCode());
		System.out.println(newInstance2.hashCode());
	}
}
