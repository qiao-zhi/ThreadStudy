package cn.qlq.thread.sixteeen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo4 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo4.class);

	public static void main(String[] args) {
		LOGGER.info("main函数所在的线程组，名字为:{}", Thread.currentThread().getThreadGroup().getName());
		LOGGER.info("main线程组的父线程组名字为:{}", Thread.currentThread().getThreadGroup().getParent().getName());
		LOGGER.info("main线程组的父线程组的父线程组的名字为:{}",
				Thread.currentThread().getThreadGroup().getParent().getParent().getName());
	}
}