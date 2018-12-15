package cn.qlq.thread.ten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadLocal的基本使用
 * 
 * @author QiaoLiQiang
 * @time 2018年12月15日下午9:00:19
 */
public class Demo2 {
	public static ThreadLocal<String> t1 = new ThreadLocal<String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

	public static void main(String[] args) {
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
