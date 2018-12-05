package cn.qlq.thread.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程优先级的方法(高优先级的大部分先执行完)
 * 
 * @author QiaoLiQiang
 * @time 2018年12月5日下午10:44:25
 */
public class PriorityMethod2 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(PriorityMethod2.class);

	public PriorityMethod2(int privority) {
		this.setPriority(privority);
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(1 * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.debug("[end]thread.getPriority->{}", this.getPriority());
	}

	public static void main(String[] args) {
		// 线程里面创建线程
		PriorityMethod2 priorityMethod1 = new PriorityMethod2(9);
		PriorityMethod2 priorityMethod2 = new PriorityMethod2(9);
		PriorityMethod2 priorityMethod3 = new PriorityMethod2(9);
		PriorityMethod2 priorityMethod4 = new PriorityMethod2(8);
		PriorityMethod2 priorityMethod5 = new PriorityMethod2(8);
		PriorityMethod2 priorityMethod6 = new PriorityMethod2(8);
		PriorityMethod2 priorityMethod7 = new PriorityMethod2(7);
		PriorityMethod2 priorityMethod8 = new PriorityMethod2(7);
		PriorityMethod2 priorityMethod9 = new PriorityMethod2(7);
		priorityMethod1.start();
		priorityMethod2.start();
		priorityMethod3.start();
		priorityMethod4.start();
		priorityMethod5.start();
		priorityMethod6.start();
		priorityMethod7.start();
		priorityMethod8.start();
		priorityMethod9.start();
	}
}
