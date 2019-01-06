package cn.qlq.thread.eighteen;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qlq.thread.seventeen.Demo5;

public class Demo3 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Demo5.class);

	public static void main(String[] args) throws InterruptedException {
		ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
		// 若没有则添加
		concurrentHashMap.putIfAbsent("1", "0101");
		System.out.println(concurrentHashMap);
		// 再次put如果存在会覆盖原值
		concurrentHashMap.put("1", "111");
		System.out.println(concurrentHashMap);
		// 替换，不管原来的值
		concurrentHashMap.replace("1", "1111");
		System.out.println(concurrentHashMap);
		// 替换，如果原来的key为1,值为111就将值替换为111
		concurrentHashMap.replace("1", "1111", "111");
		System.out.println(concurrentHashMap);
		// 替换，如果原来的key为1,值为111就删除
		concurrentHashMap.remove("1", "111");
		System.out.println(concurrentHashMap);
	}
}