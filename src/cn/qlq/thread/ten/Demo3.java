package cn.qlq.thread.ten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解决get()返回null的问题
 * 
 * @author QiaoLiQiang
 * @time 2018年12月15日下午9:16:17
 */
public class Demo3<T> extends ThreadLocal<String> {
	public static Demo3<String> t1 = new Demo3<String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);

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

	@Override
	protected String initialValue() {
		return "这是初始值" + Thread.currentThread().getName();
	}
}