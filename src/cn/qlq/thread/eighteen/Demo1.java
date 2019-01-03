package cn.qlq.thread.eighteen;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qlq.thread.seventeen.Demo5;

public class Demo1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

	public static void main(String[] args) throws InterruptedException {
		final Vector<String> vector = new Vector<>();

		synchronized(vector){
			for (int i = 0; i < vector.size(); i++) {
				System.out.println(vector.get(i));
			}
		}


		vector.add("11111111");// 添加元素

		Thread.sleep(1 * 1000);

		new Thread(new Runnable() {
			@Override
			public void run() {
				Demo1.getLast(vector);
			}
		}).start();
		// 删除元素

		Thread.sleep(1 * 100);
		new Thread(new Runnable() {
			@Override
			public void run() {
				Demo1.removeLast(vector);
			}
		}).start();

	}

	public static Object getLast(Vector<String> list) {
		synchronized (list) {
			int index = list.size() - 1;
			LOGGER.info("getLast:index ->{}", index);
			// 休眠等待第二个线程删除
			try {
				Thread.sleep(5 * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return list.get(index);
		}

	}

	public static synchronized void removeLast(Vector<String> list) {
		synchronized (list) {
			int index = list.size() - 1;
			list.remove(index);
			LOGGER.info("成功删除元素,index{},threadName->{}", index, Thread.currentThread().getName());
		}
	}
}