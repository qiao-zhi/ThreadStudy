package cn.qlq.thread.eighteen;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo4 {

	public static void main(String[] args) throws InterruptedException {
		CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
		strings.add("1");
		strings.add("2");
		strings.add("3");
		System.out.println(strings);
		Iterator<String> iterator = strings.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			if ("2".equals(next)) {
				iterator.remove();
			}
		}
		System.out.println(strings);
	}
}