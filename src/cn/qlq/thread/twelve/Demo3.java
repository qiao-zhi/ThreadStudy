package cn.qlq.thread.twelve;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读写互斥
 * 
 * @author Administrator
 *
 */
public class Demo3 {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();// 读写锁
	private static final Logger log = LoggerFactory.getLogger(Demo3.class);

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

	public static void main(String[] args) throws InterruptedException {
		final Demo3 demo1 = new Demo3();

		new Thread(new Runnable() {
			@Override
			public void run() {
				demo1.addI();
			}
		}, "t2").start();

		Thread.sleep(1 * 1000);

		new Thread(new Runnable() {
			@Override
			public void run() {
				demo1.readI();
			}
		}, "t1").start();
	}

}
