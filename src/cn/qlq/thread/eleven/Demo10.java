package cn.qlq.thread.eleven;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Administrator
 *
 */
public class Demo10 {
	private ReentrantLock lock = new ReentrantLock();

	public void awaitA() {
		lock.lock();
		System.out.println("isFair -> " + lock.isFair());
		System.out.println("isLocked -> " + lock.isLocked());
		System.out.println("isHeldByCurrentThread -> " + lock.isHeldByCurrentThread());
		lock.unlock();
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}

	public static void main(String[] args) {
		final Demo10 demo8 = new Demo10();
		demo8.awaitA();
	}
}
