package cn.qlq.thread.two;

/**
 * 守护线程
 * 
 * @author QiaoLiQiang
 * @time 2018年12月5日下午11:09:49
 */
public class DaemonThread extends Thread {
	private int i;

	@Override
	public void run() {
		while (true) {
			System.out.println(i++);
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		DaemonThread daemonThread = new DaemonThread();
		daemonThread.setDaemon(true);
		daemonThread.start();
		System.out.println(daemonThread.isDaemon());
		Thread.sleep(5000);
		System.out.println("我离开后daemonThread也结束打印");
	}

}
