package cn.qlq.thread.thirteen;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo3 {
	private static int num ;
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo3.class);
	
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<String> strings = new LinkedBlockingQueue<>(3);
		Runnable producerRun = new Runnable() {
			@Override
			public synchronized void  run() {//加同步避免出现线程非安全
				try {
					for  (int i=0;i<5;i++) {
						Thread.sleep(1000);
						String ele = "ele"+(++num);
						strings.put(ele);
						LOGGER.info("ThreadName ->{} put ele->{}",Thread.currentThread().getName(),ele);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread producer = new Thread(producerRun,"producer");
		producer.start();
		Thread producer2 = new Thread(producerRun,"producer2");
		producer2.start();
		
		Runnable consumerRun = new Runnable() {
			@Override
			public void run() {
				try {
					for (int i=0;i<5;i++) {
						Thread.sleep(3000);
						String take = strings.take();
						LOGGER.info("ThreadName ->{} take ele->{}",Thread.currentThread().getName(),take);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread consumer = new Thread(consumerRun,"consumer");
		Thread consumer1 = new Thread(consumerRun,"consumer1");
		consumer.start();
		consumer1.start();
	}
}
