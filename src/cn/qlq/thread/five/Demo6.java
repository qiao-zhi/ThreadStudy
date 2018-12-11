package cn.qlq.thread.five;

public class Demo6 {
	public static void main(String[] args) throws InterruptedException {
		Sync6 sync3 = new Sync6();
		sync3.start();
		Thread.sleep(100);
		sync3.setContinue(false);
		System.out.println("已经设置为false");
	}
}

class Sync6 extends Thread {
	private boolean isContinue = true;

	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}

	@Override
	public void run() {
		System.out.println("进入run了");
		while (isContinue == true) {
			// 同步代码块
			synchronized ("xx") {
			}
		}
		System.out.println("线程被停止了");
	}
}
