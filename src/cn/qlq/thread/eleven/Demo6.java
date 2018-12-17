package cn.qlq.thread.eleven;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 其他方法研究 getQueueLength--返回正等待此获取此锁定的线程的估计数
 * 
 * @author Administrator
 *
 */
public class Demo6 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo7.class);

	private ReentrantLock lock = new ReentrantLock(true);

	public void testMethod() {
		try {
			lock.lock();
			LOGGER.debug("testMethod lock,getHoldCount()->{},getQueueLength->{}", lock.getHoldCount(),
					lock.getQueueLength());
			// 调用yesyMethod2(),模拟锁重入
			testMethod2();
		} finally {
			lock.unlock();
			LOGGER.debug("testMethod unlock,getHoldCount()->{},getQueueLength->{}", lock.getHoldCount(),
					lock.getQueueLength());
		}
	}

	public void testMethod2() {
		try {
			lock.lock();
			LOGGER.debug("testMethod2 lock,getHoldCount()->{},getQueueLength->{}", lock.getHoldCount(),
					lock.getQueueLength());
		} finally {
			lock.unlock();
			LOGGER.debug("testMethod2 unlock,getHoldCount()->{},getQueueLength->{}", lock.getHoldCount(),
					lock.getQueueLength());
		}
	}

	public void testMethod3() {
		try {
			lock.lock();
			LOGGER.debug("testMethod3 lock,getHoldCount()->{},getQueueLength->{}", lock.getHoldCount(),
					lock.getQueueLength());
		} finally {
			lock.unlock();
			LOGGER.debug("testMethod3 unlock,getHoldCount()->{},getQueueLength->{}", lock.getHoldCount(),
					lock.getQueueLength());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Demo6 demo6 = new Demo6();
		new Thread(new Runnable() {
			public void run() {
				demo6.testMethod();
			}
		}, "thread--t1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				demo6.testMethod3();
			}
		}, "thread--t2").start();
	}
}