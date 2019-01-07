package cn.qlq.thread.nineteen;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Unsafe类的使用
 * Unsafe的直接内存访问：用Unsafe开辟的内存空间不占用Heap空间，当然也不具有自动内存回收功能。做到像C一样自由利用系统内存资源。
 * 
 * @author Administrator
 *
 */
public class Demo3 {
	public static void main(String[] args) throws Exception {
		test1();
	}

	public static long getLocation(Object object) throws Exception {
		Unsafe unsafe = getUnsafeInstance();
		Object[] array = new Object[] { object };
		long baseOffset = unsafe.arrayBaseOffset(Object[].class);// 能够获取数组第一个元素的偏移地址
		int addressSize = unsafe.addressSize();
		long location;
		switch (addressSize) {

		case 4:
			location = unsafe.getInt(array, baseOffset);
			break;
		case 8:
			location = unsafe.getLong(array, baseOffset);
			break;
		default:
			throw new Error("unsupported address size: " + addressSize);
		}
		return location;
	}

	private static void test1() throws Exception {
		Unsafe u = getUnsafeInstance();
		// 通过Unsafe 构造一个实例，即使构造方法是private声明的
		// Value value = (Value) u.allocateInstance(Value.class);
		Value value = new Value();
		System.out.println(value.getAge());

		// 静态成员遍历
		Field staticIntField = Value.class.getDeclaredField("age");
		long staticFieldOffset = u.staticFieldOffset(staticIntField);// 获取偏移量
		System.out.println("staticFieldOffset -> " + staticFieldOffset);
		u.putInt(Value.class, staticFieldOffset, 80);// 修改静态成员遍历的值
		System.out.println(value.getAge());

		// 普通成员修改(虽然是private修饰的，根据偏移量也可以取值与设置)
		Field field = Value.class.getDeclaredField("sex");
		int fieldOffset = u.fieldOffset(field);// 获取到偏移量
		System.out.println(u.getInt(value, fieldOffset));
		u.putInt(value, fieldOffset, 2);// 根据偏移量修改值
		System.out.println(value.getSex());
	}

	// 反射获取unsafe实例
	public static Unsafe getUnsafeInstance() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		return unsafe;
	}
}

class Value {
	private static int age = 25;
	private int sex = 1;

	public static int getAge() {
		return age;
	}

	public static void setAge(int age) {
		Value.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}