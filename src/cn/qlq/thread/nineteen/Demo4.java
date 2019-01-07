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
public class Demo4 {
	public static void main(String[] args) throws Exception {
		Value2 value2 = new Value2();
		System.out.println(compAndAwap(value2, 0, 5));
		System.out.println(compAndAwap(value2, 1, 5));
		System.out.println(value2.getSex());
	}

	public static boolean compAndAwap(Value2 value, int oldValue, int newValue) throws Exception {
		Unsafe u = getUnsafeInstance();

		// 普通成员修改(虽然是private修饰的，根据偏移量也可以取值与设置)
		Field field = Value2.class.getDeclaredField("sex");
		int fieldOffset = u.fieldOffset(field);// 获取到偏移量

		int int1 = u.getInt(value, fieldOffset);// 判断旧的值与期望值是否相等
		if (int1 == oldValue) {
			u.putInt(value, fieldOffset, newValue);// 根据偏移量修改值
			return true;
		}

		return false;
	}

	// 反射获取unsafe实例
	public static Unsafe getUnsafeInstance() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		return unsafe;
	}
}

class Value2 {
	private int sex = 1;

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}