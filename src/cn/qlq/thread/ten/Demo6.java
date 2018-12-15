package cn.qlq.thread.ten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 继承再修改值
 * 
 * @author QiaoLiQiang
 * @time 2018年12月15日下午9:34:41
 * @param <T>
 */
public class Demo6<T> extends InheritableThreadLocal<String> {
	public static Demo6<String> t1 = new Demo6<String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo6.class);

	public static void main(String[] args) throws InterruptedException {
		// 主线程中存入值
		t1.set("存放的值" + Thread.currentThread().getName());
		LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());

		// 创建子线程获取值
		new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());

				// 主线程中存入值
				t1.set("存放的值" + Thread.currentThread().getName());
				LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());

				// 创建子子线程获取值
				new Thread(new Runnable() {
					@Override
					public void run() {
						LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());
					}
				}, "thread2").start();
			}
		}, "thread1").start();

		Thread.sleep(2 * 1000);
		LOGGER.info("threadName - >{},值->{}", Thread.currentThread().getName(), t1.get());
	}
}