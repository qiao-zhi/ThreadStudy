package cn.qlq.thread.thirteen;

import java.util.LinkedList;
import java.util.Queue;

public class Demo1 {
	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		String poll = queue.poll();
		System.out.println(poll);
		String remove = queue.remove();
		System.out.println(remove);
	}
}
