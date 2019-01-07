package cn.qlq.thread.nineteen;

import java.lang.reflect.Field;

import com.sun.swing.internal.plaf.basic.resources.basic;

import sun.misc.Unsafe;

public class Demo5 {
	public static void main(String[] args) throws Exception {
		int[] array = new int[] { 1, 2, 3 };
		Unsafe unsafeInstance = getUnsafeInstance();
		int arrayBaseOffset = unsafeInstance.arrayBaseOffset(int[].class);// 获取基地址
		System.out.println(arrayBaseOffset);

		int scale = unsafeInstance.arrayIndexScale(int[].class);// 比例值
		int shift = 31 - Integer.numberOfLeadingZeros(scale);
		long offset = ((long) 1 << shift) + arrayBaseOffset;
		System.out.println(unsafeInstance.getInt(offset));
	}

	// 反射获取unsafe实例
	public static Unsafe getUnsafeInstance() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		return unsafe;
	}
}
