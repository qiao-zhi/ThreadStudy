package cn.qlq.thread.tone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量的使用
 * 
 * @author Administrator
 *
 */
public class Demo1 {
	public static void main(String[] args) throws InterruptedException {
		BoundedHashSet<String> boundedHashSet = new BoundedHashSet<String>(3);
		System.out.println(boundedHashSet.add("1"));
		System.out.println(boundedHashSet.add("2"));
		System.out.println(boundedHashSet.add("2"));
		System.out.println(boundedHashSet.add("3"));
		System.out.println(boundedHashSet.add("4"));// 将会一直阻塞到这里
		System.out.println("=========");
	}
}

class BoundedHashSet<T> {
	private Set<T> set;
	private Semaphore semaphore;

	public BoundedHashSet(int bound) {
		set = Collections.synchronizedSet(new HashSet());
		semaphore = new Semaphore(bound);
	}

	public boolean add(T o) throws InterruptedException {
		semaphore.acquire();// 尝试获取信号量
		boolean wasAdded = false;
		try {
			wasAdded = set.add(o);
			return wasAdded;
		} finally {
			if (!wasAdded) {// 如果添加失败就释放信号量，添加成功就占用一个信号量
				semaphore.release();
			}
		}
	}

	public boolean remove(T o) throws InterruptedException {
		boolean remove = set.remove(o);
		if (remove)// 如果删除成功之后就释放一个信号量
			semaphore.release();
		return remove;
	}
}
