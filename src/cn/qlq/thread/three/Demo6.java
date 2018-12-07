package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * for循环检测是否是停止状态，如果是停止状态就不执行后面代码
 * 
 * @author Administrator
 *
 */
public class Demo6 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo4.class);

	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 50; i++) {
				if (Thread.interrupted()) {
					log.debug("线程已经终止,我要退出");
					// 抛出异常
					throw new InterruptedException();
				}
				log.debug("i={}", i);
			}
			log.debug("for 后面的语句");
		} catch (InterruptedException e) {
			log.error("进入catch方法---", e);
		}
	}

	public static void main(String[] args) {
		try {
			Demo6 demo6 = new Demo6();
			demo6.start();
			Thread.sleep(3);
			// 发出中断信号
			demo6.interrupt();
		} catch (InterruptedException e) {
			log.error("InterruptedException ", e);
		}
		log.debug("end");
	}
}
