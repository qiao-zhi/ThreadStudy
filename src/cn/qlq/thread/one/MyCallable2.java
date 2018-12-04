package cn.qlq.thread.one;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 单个线程接收返回值
 * 
 * @author QiaoLiQiang
 * @time 2018年12月4日下午10:28:36
 */
public class MyCallable2 implements Callable<String> {

	/**
	 * 实现call方法，接口中抛出异常。因为子类不可以比父类干更多的坏事，所以子类可以不抛出异常
	 */
	@Override
	public String call() {
		System.out.println(Thread.currentThread().getName() + "   执行callable的call方法");
		return "result";
	}

	public static void main(String[] args) {
		test1();
	}

	/**
	 * 单个线程
	 */
	public static void test1() {
		// 1.创建固定大小的线程池
		ExecutorService es = Executors.newFixedThreadPool(1);
		// 2.提交线程任务，用Future接口接受返回的实现类
		Future<String> future = es.submit(new MyCallable());
		// 3.关闭线程池
		es.shutdown();
		// 4.调用future.get()获取callable执行完成的返回结果
		String result;
		try {
			result = future.get();
			System.out.println(Thread.currentThread().getName() + "\t" + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}