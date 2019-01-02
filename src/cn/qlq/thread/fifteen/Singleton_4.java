package cn.qlq.thread.fifteen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 静态内置类实现单例模式
 * 
 * @author Administrator
 *
 */
public class Singleton_4 implements Serializable {

	/**
	 * serialize id
	 */
	private static final long serialVersionUID = 4079299493423471357L;

	private static class Singleton_4Handle {
		private static final Singleton_4 instance = new Singleton_4();
	}

	private Singleton_4() {
	}

	public static Singleton_4 getInstance() {
		return Singleton_4Handle.instance;
	}

	protected Object readResolve() {
		System.out.println("调用readResolve方法");
		return Singleton_4Handle.instance;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Singleton_4 instance = Singleton_4.getInstance();

		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("tt.txt")));
		outputStream.writeObject(instance);
		outputStream.flush();
		outputStream.close();
		System.out.println(instance.hashCode());

		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("tt.txt")));
		Singleton_4 readObject = (Singleton_4) inputStream.readObject();// 会自动调用readResolve
		inputStream.close();
		System.out.println(readObject.hashCode());
	}
}
