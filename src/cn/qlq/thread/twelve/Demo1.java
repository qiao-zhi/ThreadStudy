package cn.qlq.thread.twelve;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qlq.thread.one.RunnableThread;

public class Demo1 {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();// 读写锁
	private static final Logger log = LoggerFactory.getLogger(Demo1.class);

	private int i;

	public String readI() {
		try {
			lock.readLock().lock();// 占用读锁
			log.info("threadName -> {} 占用读锁,i->{}", Thread.currentThread().getName(), i);
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {

		} finally {
			log.info("threadName -> {} 释放读锁,i->{}", Thread.currentThread().getName(), i);
			lock.readLock().unlock();// 释放读锁
		}
		return i + "";
	}

	public static void main(String[] args) {
		final Demo1 demo1 = new Demo1();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				demo1.readI();
			}
		};

		new Thread(runnable, "t1").start();
		new Thread(runnable, "t2").start();
		new Thread(runnable, "t3").start();

	}

}
