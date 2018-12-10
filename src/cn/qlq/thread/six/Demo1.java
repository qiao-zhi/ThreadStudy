package cn.qlq.thread.six;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sleep()结合while(true)实现多个线程间通信
 * 
 * @author QiaoLiQiang
 * @time 2018年12月10日下午9:32:52
 */
public class Demo1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);

	private static volatile List list = new ArrayList<>();// 加volatile关键字是为了每次从主存中读取数据，否则两个线程读取到的不一致

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						LOGGER.debug("add ele ->{},threadName->{}", i, Thread.currentThread().getName());
						list.add(String.valueOf(i));
						Thread.sleep(1 * 1000);
					}
				} catch (InterruptedException e) {
					LOGGER.error("InterruptedException", e);
				}
			}
		}, "A").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (list.size() == 5) {
							LOGGER.debug("list大小为5 了，线程B要退出了");
							throw new InterruptedException();
						}
					}
				} catch (InterruptedException e) {
					LOGGER.error("抛出异常，线程退出", e);
				}
			}
		}, "B").start();
	}
}
