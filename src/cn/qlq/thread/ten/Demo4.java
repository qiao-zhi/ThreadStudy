package cn.qlq.thread.ten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主线程中设置值，子线程中获取值
 * 
 * @author QiaoLiQiang
 * @time 2018年12月15日下午9:29:40
 * @param <T>
 */
public class Demo4<T> extends InheritableThreadLocal<String> {
	public static Demo4<String> t1 = new Demo4<String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo4.class);

	public static void main(String[] args) {
		// 主线程中存入值
		t1.set("存放的值" + Thread.currentThread().getName());

		new Thread(new Runnable() {
			@Override
			public void run() {
				if (t1.get() == null) {
					LOGGER.info("从未放过值,threadName->{}", Thread.currentThread().getName());
					t1.set("存放的值" + Thread.currentThread().getName());
				}
				LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());
			}
		}, "thread1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				if (t1.get() == null) {
					LOGGER.info("从未放过值,threadName->{}", Thread.currentThread().getName());
					t1.set("存放的值" + Thread.currentThread().getName());
				}
				LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());
			}
		}, "thread2").start();
	}
}