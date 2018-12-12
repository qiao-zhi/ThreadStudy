package cn.qlq.thread.six;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * wait条件发生变化导致程序混乱 (一个线程向集合中添加元素，两个线程从集合中删除元素)
 * 
 * @author Administrator
 *
 */
public class Demo10 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo10.class);

	public static void main(String[] args) throws InterruptedException {
		final List<String> list = new ArrayList<String>();

		// 删除元素线程1
		Thread sub1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (list) {
						while (list.size() == 0) {
							LOGGER.info("wait start---------------,threadName->{}", Thread.currentThread().getName());
							// 等待10秒钟自动唤醒
							list.wait();
							LOGGER.info("wait end---------------,threadName->{}", Thread.currentThread().getName());
						}

						LOGGER.info("list.remove ->{}, threadName->{}", list.get(0), Thread.currentThread().getName());
						list.remove(0);

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
						while (list.size() == 0) {
							LOGGER.info("wait start---------------,threadName->{}", Thread.currentThread().getName());
							// 等待10秒钟自动唤醒
							list.wait();
							LOGGER.info("wait end---------------,threadName->{}", Thread.currentThread().getName());
						}

						LOGGER.info("list.remove ->{}, threadName->{}", list.get(0), Thread.currentThread().getName());
						list.remove(0);

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
				synchronized (list) {
					list.add("1");
					LOGGER.info("添加元素->{},threadName->{}", "1", Thread.currentThread().getName());
					list.notifyAll();
					LOGGER.info("threadName->{} 执行list.notifyAll() 发出通知唤醒所有阻塞线程", Thread.currentThread().getName());

				}
			}
		}, "B");
		addThread.start();
	}
}
