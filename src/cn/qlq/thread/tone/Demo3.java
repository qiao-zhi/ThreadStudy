package cn.qlq.thread.tone;

import java.util.concurrent.Exchanger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Administrator
 *
 */
public class Demo3 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);

	public static void main(String[] args) throws InterruptedException {
		final Exchanger<String> exchanger = new Exchanger<String>();// 泛型指定交换的数据
		for (int i = 0; i < 4; i++) {
			Thread.sleep(2 * 1000);
			new Thread(new Runnable() {
				@Override
				public void run() {
					LOGGER.info("threadName -> {}", Thread.currentThread().getName());
					try {
						String exchange = exchanger.exchange(Thread.currentThread().getName());
						LOGGER.error("threadName -> {},exchange->{}", Thread.currentThread().getName(), exchange);
					} catch (Exception e) {
						e.printStackTrace();
					}
					LOGGER.info("threadName -> {}", Thread.currentThread().getName());
				}
			}).start();
		}
	}
}
