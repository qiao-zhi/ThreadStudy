package cn.qlq.thread.thirteen;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo6 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo6.class);

	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<DelayObj> delayObjs = new DelayQueue<DelayObj>();
		DelayObj delayObj = new DelayObj("1");
		delayObjs.put(delayObj);
		LOGGER.info("放入元素->{}", delayObj);
		Thread.sleep(1 * 1000);

		DelayObj delayObj2 = new DelayObj("3");
		delayObjs.put(delayObj2);
		LOGGER.info("放入元素->{}", delayObj2);

		LOGGER.info("{}", delayObjs.take());
		LOGGER.info("{}", delayObjs.take());

	}
}

class DelayObj implements Delayed {
	private Date createTime;
	private String name;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public DelayObj(String name) {
		this.createTime = new Date();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Delayed o) { // 返回负数表示在前面，返回正数表示在后面
		if (this.getDelay(TimeUnit.NANOSECONDS) > o.getDelay(TimeUnit.NANOSECONDS)) {// NANOSECONDS是十亿分之秒
			return -1;
		} else if (this.getDelay(TimeUnit.NANOSECONDS) < o.getDelay(TimeUnit.NANOSECONDS)) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "DelayObj [createTime=" + createTime + ", name=" + name + "]";
	}

	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = createTime.getTime() + 5 * 1000 - now.getTime();
		System.out.println(diff);
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}
}
