package cn.qlq.thread.seven;

public class Demo4 {
	public static void main(String[] args) {
		MyResource myResource = new MyResource();
		// 多个生产者一个消费者
		MyConsumerThread myConsumerThread = new MyConsumerThread(myResource);
		// MyConsumerThread myConsumerThread1 = new
		// MyConsumerThread(myResource);
		// MyConsumerThread myConsumerThread2 = new
		// MyConsumerThread(myResource);
		MyProducerThread myProducerThread = new MyProducerThread(myResource);
		MyProducerThread myProducerThread1 = new MyProducerThread(myResource);
		MyProducerThread myProducerThread2 = new MyProducerThread(myResource);
		myProducerThread.start();
		myProducerThread1.start();
		myProducerThread2.start();
		myConsumerThread.start();
		// myConsumerThread1.start();
		// myConsumerThread2.start();
	}
}

/**
 * 资源类 一个加一个减(都有同步锁)
 * 
 * @author: qlq
 * @date : 2018年6月15日上午11:38:37
 */
class MyResource {
	private int num;// 资源数量
	private int capacity = 10;// 资源容量

	/**
	 * 同步方法增加资源 如果数量大于容量，线程进入阻塞状态 否则通知消费者进行消费
	 */
	public synchronized void add() {
		if (num >= capacity) {// 大于等于的话进入阻塞状态
			try {
				wait();
				System.out.println(Thread.currentThread().getName() + "进入线程等待。。。");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			num++;// 生产一件资源
			System.out.println(Thread.currentThread().getName() + "生产一件资源，目前剩余资源" + num + "件");
			notifyAll();// 通知消费者进行消费
		}
	}

	/**
	 * 同步方法移除资源 如果num>0,消费资源，通知生产者进行生产 否则的话进入阻塞队列
	 */
	public synchronized void remove() {
		if (num > 0) {
			num--;
			System.out.println(Thread.currentThread().getName() + "消费一件资源，目前剩余" + num + "件");
			notifyAll();// 唤醒生产者进行生产
		} else {
			try {
				wait();
				System.out.println(Thread.currentThread().getName() + "进入线程等待。。。");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 生产者类
 * 
 * @author: qlq
 * @date : 2018年6月16日上午11:08:05
 */
class MyProducerThread extends Thread {
	private MyResource resource;

	protected MyProducerThread(MyResource resource) {
		super();
		this.resource = resource;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resource.add();
		}
	}
}

/**
 * 消费者线程
 * 
 * @author: qlq
 * @date : 2018年6月16日上午11:09:06
 */
class MyConsumerThread extends Thread {
	private MyResource resource;

	protected MyConsumerThread(MyResource resource) {
		super();
		this.resource = resource;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resource.remove();
		}
	}
}