package cn.qlq.thread.two;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 研究其他方法的使用
 * 
 * @author QiaoLiQiang
 * @time 2018年12月5日下午10:21:16
 */
public class OtherMethod extends Thread {

	private static final Logger log = LoggerFactory.getLogger(OtherMethod.class);

	@Override
	public void run() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OtherMethod otherMethod = new OtherMethod();
		otherMethod.start();
		log.debug("getName()->{}", otherMethod.getName());// 获取名称
		log.debug("getPriority()->{}", otherMethod.getPriority());// 获取优先级

		ThreadGroup threadGroup = otherMethod.getThreadGroup();// 获取线程组
		log.debug("threadGroup - > {}", threadGroup);

		// 修改优先级
		otherMethod.setPriority(9);
		log.debug("getPriority()->{}", otherMethod.getPriority());

		// 修改名称
		otherMethod.setName("newName");
		log.debug("getName()->{}", otherMethod.getName());

		// 获取所有线程的堆栈信息
		Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
		for (Map.Entry<Thread, StackTraceElement[]> stackTrace : allStackTraces.entrySet()) {
			Thread thread = (Thread) stackTrace.getKey();
			StackTraceElement[] stackTraceElements = stackTrace.getValue();
			log.debug(" 线程->" + thread.getName());
			for (StackTraceElement s : stackTraceElements) {
				log.debug(" 线程stackTraceElements->" + s);
			}
		}

	}
}
