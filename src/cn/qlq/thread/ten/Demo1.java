package cn.qlq.thread.ten;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadLocal的基本使用
 * 
 * @author QiaoLiQiang
 * @time 2018年12月15日下午9:00:19
 */
public class Demo1 {
	public static ThreadLocal<String> t1 = new ThreadLocal<String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo1.class);

	public static void main(String[] args) {
		if (t1.get() == null) {
			LOGGER.info("从未放过值");
			t1.set("存放的值");
		}
		LOGGER.info("{}", t1.get());
		LOGGER.info("{}", t1.get());
	}
}
