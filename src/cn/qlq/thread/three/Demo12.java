package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo12 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo12.class);
	private long i;

	@Override
	public void run() {
		while (true) {
			i++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 暴力停止
		Demo12 demo12 = new Demo12();
		demo12.start();

		// A段
		Thread.sleep(500);
		demo12.suspend();
		log.debug("time1->{},i->{}", System.currentTimeMillis(), demo12.getI());
		Thread.sleep(500);
		log.debug("time2->{},i->{}", System.currentTimeMillis(), demo12.getI());

		// B段
		demo12.resume();
		Thread.sleep(500);

		// C段
		Thread.sleep(500);
		demo12.suspend();
		log.debug("time3->{},i->{}", System.currentTimeMillis(), demo12.getI());
		Thread.sleep(500);
		log.debug("time4->{},i->{}", System.currentTimeMillis(), demo12.getI());
	}

	public long getI() {
		return i;
	}

	public void setI(long i) {
		this.i = i;
	}
}
