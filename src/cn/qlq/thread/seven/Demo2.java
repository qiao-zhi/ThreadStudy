package cn.qlq.thread.seven;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.util.logging.resources.logging;

/**
 * 多生产与多消费:操作值-假死( 多生产与多消费保证只有一个元素生产与消费)
 * 
 * @author Administrator
 *
 */
public class Demo2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo2.class);

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
								LOGGER.info("进入等待***,threadName->{}", Thread.currentThread().getName());
								list.wait();
								LOGGER.info("退出等待***,threadName->{}", Thread.currentThread().getName());
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
								LOGGER.info("进入等待***,threadName->{}", Thread.currentThread().getName());
								list.wait();
								LOGGER.info("退出等待***,threadName->{}", Thread.currentThread().getName());
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
					for (int i = 0; i < 50000; i++) {
						synchronized (list) {
							while (list.size() != 0) {
								LOGGER.info("进入等待***,threadName->{}", Thread.currentThread().getName());
								list.wait();
								LOGGER.info("退出等待***,threadName->{}", Thread.currentThread().getName());
							}
							list.add(i + "");
							LOGGER.info("添加元素->{},threadName->{}", i, Thread.currentThread().getName());
							list.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					LOGGER.error("InterruptedException error", e);
				}
			}
		}, "add1");
		addThread.start();

		// 增加元素线程
		Thread.sleep(1 * 1000);
		Thread addThread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 50000; i++) {
						synchronized (list) {
							while (list.size() != 0) {
								LOGGER.info("进入等待***,threadName->{}", Thread.currentThread().getName());
								list.wait();
								LOGGER.info("退出等待***,threadName->{}", Thread.currentThread().getName());
							}
							list.add(i + "");
							LOGGER.info("添加元素->{},threadName->{}", Thread.currentThread().getName());
							list.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					LOGGER.error("InterruptedException error", e);
				}
			}
		}, "add2");
		addThread2.start();

		Thread.sleep(10 * 1000);
		LOGGER.info("sub1 state->{}", sub1.getState());
		LOGGER.info("sub2 state->{}", sub2.getState());
		LOGGER.info("add1 state->{}", addThread.getState());
		LOGGER.info("add2 state->{}", addThread2.getState());
	}
}
