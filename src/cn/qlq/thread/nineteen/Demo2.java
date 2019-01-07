package cn.qlq.thread.nineteen;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
	public static void main(String[] args) throws InterruptedException {
		AtomicInteger count = new AtomicInteger(0);
		System.out.println(count.incrementAndGet());// 先加后用，相当于++i
		System.out.println(count.getAndIncrement());// 先用后加，相当于i++
		System.out.println(count.intValue());
		System.out.println("===========");
		System.out.println(count.decrementAndGet());// 先减后用，相当于--i
		System.out.println(count.getAndDecrement());// 先用后减，相当于i--
		System.out.println(count.intValue());

		System.out.println("===========");
		count.set(10);
		System.out.println(count.addAndGet(5));// 先加后用
		System.out.println(count.getAndAdd(5));// 先用后加
		System.out.println(count.intValue());

		System.out.println("===========");
		System.out.println(count.getAndSet(100));// 获取原值，并用新值覆盖
		System.out.println(count.intValue());

		System.out.println("===========");
		System.out.println(count.compareAndSet(100, 850));// 如果是100就设为850
		System.out.println(count.compareAndSet(100, 800));// 如果是100就设为800
		System.out.println(count.intValue());
	}

}
