package cn.qlq.thread.seventeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo5 extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

	@Override
	public void run() {
		int i = 1 / 0;
	}

	public static void main(String[] args) {
		// 设置全局的
		Demo5.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				LOGGER.error("所有Demo5线程默认的异常处理器, threadName -> {}", t.getName(), e);
			}
		});

		Demo5 demo5 = new Demo5();
		// 设置单独的
		demo5.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				LOGGER.error("单独给线程设置的, threadName -> {}", t.getName(), e);
			}
		});
		demo5.start();
	}
}