package cn.qlq.thread.eleven;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentrantLock結合Condition实现生产者-消费者模型(单生产-多消费)
 * 
 * @author Administrator
 *
 */
public class Demo4 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo4.class);
	private Lock lock = new ReentrantLock(false);
	private Condition producerCon = lock.newCondition();
	private Condition consumerCon = lock.newCondition();
	private volatile List<String> list = new ArrayList<String>();// 模拟是一个容器
	private volatile int capacity = 3;// 最多十个

	public void removeEle() {
		try {
			lock.lock();
			for (int i = 0; i < 5; i++) {
				while (list.size() == 0) {
					consumerCon.await();// 阻塞消费者
				}
				LOGGER.info("threadName - > {} 消费元素 {}", Thread.currentThread().getName(), list.get(0));
				list.remove(0);
				producerCon.signal();// 唤醒生产者，由于是单个生产者，所以可以用signal()
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void addEle() {
		try {
			lock.lock();
			for (int i = 0; i < 10; i++) {
				// 超过容量阻塞生产者
				while (list.size() >= 1) {
					producerCon.await();
				}
				LOGGER.info("threadName - > {} 生产元素 {}", Thread.currentThread().getName(), i);
				list.add(i + "");
				consumerCon.signalAll();// 唤醒消费者，由于是多个消费者，所以可以用signalAll()
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Demo4 demo4 = new Demo4();
		Runnable consumer = new Runnable() {
			@Override
			public void run() {
				demo4.removeEle();
			}
		};
		Runnable producer = new Runnable() {
			@Override
			public void run() {
				demo4.addEle();
			}
		};
		// 两个消费者
		new Thread(consumer, "con1").start();

		new Thread(consumer, "con2").start();

		// 单个生产者
		new Thread(producer, "pro1").start();
	}
}
