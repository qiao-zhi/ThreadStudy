package cn.qlq.thread.ten;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class Demo7 {
	public static ThreadLocal t1 = new ThreadLocal();
	public static ThreadLocal<String> t2 = new ThreadLocal<String>();

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo7.class);

	public static void main(String[] args) throws InterruptedException {
		Map data = new HashMap();
		data.put("str", "111222");
		data.put("int", 11122);
		data.put("obj", new Object());
		t1.set(data);

		t2.set("t2");

		System.out.println(t1.get());
		System.out.println(t2.get());
	}
}