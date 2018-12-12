package cn.qlq.thread.six;

/**
 * 不在同步中调用wait
 * 
 * @author Administrator
 *
 */
public class Demo2 {
	public static void main(String[] args) throws InterruptedException {
		Object object = new Object();
		object.wait();
	}
}
