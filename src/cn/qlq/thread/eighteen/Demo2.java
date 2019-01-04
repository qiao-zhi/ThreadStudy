package cn.qlq.thread.eighteen;

import java.util.Iterator;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qlq.thread.seventeen.Demo5;

public class Demo2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

	public static void main(String[] args) throws InterruptedException {
		final Vector<String> vector = new Vector<>();
		vector.add("111");
		vector.add("222");
		vector.add("333");
		Iterator<String> iterator = vector.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			if ("111".equals(next)) {
				iterator.remove();
			}
		}
		System.out.println(vector);
	}
}