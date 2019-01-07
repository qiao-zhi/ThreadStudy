package cn.qlq.thread.nineteen;

import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.Unsafe;

public class Demo5 {
	public static void main(String[] args) throws Exception {
		int[] array = new int[] { 1, 2, 3 };
		System.out.println(Arrays.toString(array));
		Unsafe unsafeInstance = getUnsafeInstance();
		// 获取数组的基地址
		int arrayBaseOffset = unsafeInstance.arrayBaseOffset(int[].class);
		System.out.println(arrayBaseOffset);

		// 比例值--每个元素占的大小
		int scale = unsafeInstance.arrayIndexScale(int[].class);
		System.out.println(scale);

		// 修改index为1元素的值为22
		unsafeInstance.putInt(array, arrayBaseOffset + 1 * scale, 22);

		// 采用元素加地址偏移量或者元素的值
		for (int i = 0; i < array.length; i++) {
			System.out.println(unsafeInstance.getInt(array, arrayBaseOffset + i * scale));
		}
	}

	// 反射获取unsafe实例
	public static Unsafe getUnsafeInstance() throws Exception {
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		return unsafe;
	}
}
