package cn.qlq.thread.nineteen;

import java.lang.reflect.Field;

import sun.awt.SunHints.Value;
import sun.misc.Unsafe;

/**
 * Unsafe类的使用
 * Unsafe的直接内存访问：用Unsafe开辟的内存空间不占用Heap空间，当然也不具有自动内存回收功能。做到像C一样自由利用系统内存资源。
 * 
 * @author Administrator
 *
 */
public class Demo1 {
	public static void main(String[] args) throws Exception {
		test2();
	}

	private static void test3() throws Exception {
		Unsafe u = getUnsafeInstance();
		System.out.println(u.ADDRESS_SIZE);
		System.out.println(u.pageSize());
	}

	private static void test2() throws Exception {
		Unsafe u = getUnsafeInstance();
		// 分配一块内存
		long allocateMemory = u.allocateMemory(1000);
		System.out.println(allocateMemory);
		// 向刚分配的内存中存入值
		u.putChar(allocateMemory, 'A');
		// 获取值
		System.out.println(u.getChar(allocateMemory));

		u.freeMemory(allocateMemory);
		System.out.println(u.getChar(allocateMemory));
	}

	private static void test1() throws Exception {
		Unsafe u = getUnsafeInstance();
		Per per = (Per) u.allocateInstance(Per.class);// 构造一个实例，即使构造方法是private声明的
		System.out.println(per.getAge());

		// 静态成员遍历
		Field staticIntField = Per.class.getDeclaredField("age");
		long staticFieldOffset = u.staticFieldOffset(staticIntField);
		u.putInt(per.getClass(), staticFieldOffset, 80);// 修改静态成员遍历的值
		System.out.println(per.getAge());

		// 普通成员修改
		Field Field = Per.class.getDeclaredField("name");
		System.out.println(per.getName());
		int fieldOffset = u.fieldOffset(Field);
		u.putObject(per, fieldOffset, "zhangsan");// 修改值
		System.out.println(per.getName());
	}

	// 反射获取unsafe实例
	public static Unsafe getUnsafeInstance() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		return unsafe;
	}
}

class Per {
	private static int age = 25;
	private int sex = 0;
	private String name;
	private String address;

	public static int getAge() {
		return age;
	}

	public static void setAge(int age) {
		Per.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}