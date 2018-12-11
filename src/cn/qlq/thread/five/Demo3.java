package cn.qlq.thread.five;

public class Demo3 {
	public static void main(String[] args) throws InterruptedException {
		Sync3 sync3 = new Sync3();
		sync3.start();
		Thread.sleep(100);
		sync3.setContinue(false);
		System.out.println("已经设置为false");
	}
}

class Sync3 extends Thread {
	private volatile boolean isContinue = true;

	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}

	@Override
	public void run() {
		System.out.println("进入run了");
		while (isContinue == true) {

		}
		System.out.println("线程被停止了");
	}
}
