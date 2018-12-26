package cn.qlq.thread.thirteen;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo5 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<String> strings = new SynchronousQueue<String>();
		
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
						String take = strings.take();
						LOGGER.info("ThreadName ->{} take ele->{}",Thread.currentThread().getName(),take);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"consumer");
		consumer.start();
		
		strings.put("1");
		LOGGER.info("放入元素 1");
	}
}