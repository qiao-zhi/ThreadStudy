package cn.qlq.thread.one;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现callable接口，实现Callable接口
 * 
 *
 */
public class MyCallable implements Callable<String> {

	private static final Logger log = LoggerFactory.getLogger(MyCallable.class);

	/**
	 * 实现call方法，接口中抛出异常。因为子类不可以比父类干更多的坏事，所以子类可以不抛出异常
	 */
	@Override
	public String call() {
		log.debug(Thread.currentThread().getName() + "   执行callable的call方法");
		return "result";
	}

	public static void main(String[] args) {
		// 1.创建callable对象
		Callable<String> myCallable = new MyCallable();
		// 2.由上面的callable对象创建一个FutureTask对象
		FutureTask<String> oneTask = new FutureTask<String>(myCallable);
		// 3.由FutureTask创建一个Thread对象
		Thread t = new Thread(oneTask);
		// 4.开启线程
		t.start();
		log.debug("运行结束,threadname->{}", Thread.currentThread().getName());
	}

}