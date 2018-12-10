package cn.qlq.thread.four;

import cn.qlq.thread.four.PublicClass.PrivateClass;

public class Demo16 {
	public static void main(String[] args) {
		final PrivateClass privateClass = new PrivateClass();
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				privateClass.test1();
			}
		}, "A").start();
		// 访问同步方法
		new Thread(new Runnable() {
			@Override
			public void run() {
				privateClass.test2(privateClass);
			}
		}, "B").start();
	}
}
