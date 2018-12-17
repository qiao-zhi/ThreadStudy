package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentrantLock的基本使用方法
 * @author Administrator
 *
 */
public class Demo1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);
	private Lock lock = new ReentrantLock();
	
	public void testMethod(){
		try {
			LOGGER.info("threadName -> {} enter testMethod",Thread.currentThread().getName());
			lock.lock();
			LOGGER.info("threadName -> {} lock",Thread.currentThread().getName());
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			LOGGER.info("threadName -> {} unlock",Thread.currentThread().getName());
			lock.unlock();
		}
		LOGGER.info("threadName -> {} exit testMethod",Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		final Demo1 demo1 = new Demo1();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo1.testMethod();
			}
		},"threadA").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo1.testMethod();
			}
		},"threadB").start();
	}
}
