package cn.qlq.thread.ttwo;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneShotLatch {
	private static final Logger log = LoggerFactory.getLogger(OneShotLatch.class);
	private final Sync sync = new Sync();

	public void signal() {
		sync.releaseShared(0);
	}

	public void await() throws InterruptedException {
		sync.acquireSharedInterruptibly(0);
	}

	private class Sync extends AbstractQueuedSynchronizer {
		@Override
		protected int tryAcquireShared(int arg) {
			// 如果闭锁是开的(state == 1),那么这个操作将成功，否则会失败
			return getState() == 1 ? 1 : -1;
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			setState(1);// 打开锁
			return true;// 返回成功
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final OneShotLatch oneShotLatch = new OneShotLatch();
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					log.info("threadname " + Thread.currentThread().getName() + "\t start");
					try {
						oneShotLatch.await();
						log.info("threadname " + Thread.currentThread().getName() + "\t end");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		Thread.sleep(5 * 1000);
		oneShotLatch.signal();
	}
}
