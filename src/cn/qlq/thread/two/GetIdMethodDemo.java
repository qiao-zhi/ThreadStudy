package cn.qlq.thread.two;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 研究getId
 * 
 * @author Administrator
 *
 */
public class GetIdMethodDemo extends Thread {

	private static final Logger log = LoggerFactory.getLogger(GetIdMethodDemo.class);

	@Override
	public void run() {
		try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
		}
		log.debug("threadName->{},getId->{}", Thread.currentThread().getName(), Thread.currentThread().getId());
	}

	public static void main(String[] args) throws InterruptedException {
		log.debug("threadName->{},getId->{}", Thread.currentThread().getName(), Thread.currentThread().getId());
		Map<Thread, StackTraceElement[]> allStackTraces = Thread.currentThread().getAllStackTraces();
		Set<Thread> keySet = allStackTraces.keySet();
		System.out.println(keySet.size());
		System.out.println(Thread.currentThread().getId());
		for (int i = 0; i < 10; i++) {
			GetIdMethodDemo t1 = new GetIdMethodDemo();
			t1.start();
		}
	}
}
