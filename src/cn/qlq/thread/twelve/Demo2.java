package cn.qlq.thread.twelve;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2 {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();// 读写锁
	private static final Logger log = LoggerFactory.getLogger(Demo2.class);

	private int i;

	public void addI() {
		try {
			lock.writeLock().lock();// 占用写锁
			log.info("threadName -> {} 占用写锁,i->{}", Thread.currentThread().getName(), i);
			Thread.sleep(2 * 1000);
			i++;
		} catch (InterruptedException e) {

		} finally {
			log.info("threadName -> {} 释放写锁,i->{}", Thread.currentThread().getName(), i);
			lock.writeLock().unlock();// 释放写锁
		}
	}

	public static void main(String[] args) {
		final Demo2 demo1 = new Demo2();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				demo1.addI();
			}
		};

		new Thread(runnable, "t1").start();
		new Thread(runnable, "t2").start();
		new Thread(runnable, "t3").start();

	}

}
