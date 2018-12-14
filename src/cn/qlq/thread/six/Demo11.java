package cn.qlq.thread.six;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 交替打印特殊符号
 * 奇数线程打印☆，偶数线程打印★
 * 
 * @author Administrator
 *
 */
public class Demo11 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo11.class);

	public volatile static boolean isOddThread = true; // 标记是不是奇数线程

	public static void main(String[] args) throws InterruptedException {
		final Object lock = new Object();
		
		//10个奇数线程打印☆
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						synchronized (lock) {
							while (!isOddThread) {
								lock.wait();
							}
							LOGGER.info("ThreadName->{}, print ->{}",Thread.currentThread().getName(),"☆");
							isOddThread  = false;
							lock.notifyAll();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "odd" + i).start();
		}
		
		//10个偶数线程打印★
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						synchronized (lock) {
							while (isOddThread) {
								lock.wait();
							}
							LOGGER.info("ThreadName->{}, print ->{}",Thread.currentThread().getName(),"★");
							isOddThread  = true;
							lock.notifyAll();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "even" + i).start();
		}

	}
}
