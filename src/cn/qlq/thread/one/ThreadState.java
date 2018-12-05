package cn.qlq.thread.one;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadState extends Thread {

	private static final Logger log = LoggerFactory.getLogger(ThreadState.class);

	@Override
	public void run() {// 休眠5秒钟线程进入超时等待状态
		try {
			Thread.sleep(5 * 1000);
			synchronized (log) {// 模拟占用锁十秒钟
				Thread.sleep(10 * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 这个线程只是为了占用锁，所以先启动之后线程休眠2s启动第二个线程
		ThreadState threadState_0 = new ThreadState();
		threadState_0.start();
		Thread.sleep(2 * 1000);

		ThreadState threadState = new ThreadState();
		log.debug("state1->{}", threadState.getState());// 新建状态
		threadState.start();
		log.debug("state2->{}", threadState.getState());// 此时应当处于就绪状态
		Thread.sleep(1 * 1000);
		log.debug("state3->{}", threadState.getState());// 主线程休眠了3秒钟，threadState应该在休眠5秒钟，处于超时等待状态
		Thread.sleep(7 * 1000);
		log.debug("state4->{}", threadState.getState());// 主线程休眠了10秒钟，threadState应该在等待同步锁
		Thread.sleep(20 * 1000);
		log.debug("state5->{}", threadState.getState());// 主线程休眠了30秒钟，线程应该结束

	}
}