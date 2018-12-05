package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程优先级的方法
 * 
 * @author QiaoLiQiang
 * @time 2018年12月5日下午10:44:25
 */
public class PriorityMethod extends Thread {

	private static final Logger log = LoggerFactory.getLogger(PriorityMethod.class);

	@Override
	public void run() {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1 * 100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(r1);
		thread.start();
		log.debug("thread.getPriority->{}", thread.getPriority());
	}

	public static void main(String[] args) {
		// 线程里面创建线程
		PriorityMethod priorityMethod = new PriorityMethod();
		priorityMethod.setPriority(9);
		priorityMethod.start();
	}
}
