package cn.qlq.thread.eleven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用condition实现线程按顺序执行(比如创建10个线程，每个线程打印自己的名字，按照1-10打印)
 * 
 * @author Administrator
 *
 */
public class Demo14 {
	private ReentrantLock lock = new ReentrantLock();
	private Condition newCondition = lock.newCondition();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo14.class);

	private int currentNum = 1;// 标记当前线程执行到第几个线程

	public void printName() {
		try {
			lock.lock();
			while (!String.valueOf(currentNum).equals(Thread.currentThread().getName())) {
				newCondition.await();
			}
			LOGGER.info("threadName - > {} ", Thread.currentThread().getName());
			newCondition.signalAll();
			currentNum++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Demo14 demo8 = new Demo14();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				demo8.printName();
			}
		};

		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(runnable, (i + 1) + "");
		}
		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}

	}
}