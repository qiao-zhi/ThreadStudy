package cn.qlq.thread.seven;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用之前的等待/通知实现一个简单的生产者消费者
 * 
 * @author Administrator
 *
 */
public class Demo1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);

	public static void main(String[] args) throws InterruptedException {
		final List<String> list = new ArrayList<String>();

		// 删除元素线程1
		Thread sub1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (list) {
						while (true) {
							while (list.size() == 0) {
								list.wait();
							}

							LOGGER.info("list.remove ->{}, threadName->{}", list.get(0),
									Thread.currentThread().getName());
							list.remove(0);
							list.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "sub1");
		sub1.start();

		// 删除元素线程2
		Thread sub2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (list) {
						while (true) {
							while (list.size() == 0) {
								list.wait();
							}

							LOGGER.info("list.remove ->{}, threadName->{}", list.get(0),
									Thread.currentThread().getName());
							list.remove(0);
							list.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "sub2");
		sub2.start();

		// 增加元素线程
		Thread.sleep(1 * 1000);
		Thread addThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 5; i++) {
						synchronized (list) {
							list.add(i + "");
							LOGGER.info("添加元素->{},threadName->{}", i, Thread.currentThread().getName());
							list.notifyAll();
							list.wait();
						}
					}
				} catch (InterruptedException e) {
					LOGGER.error("InterruptedException error", e);
				}
			}
		}, "B");
		addThread.start();
	}
}
