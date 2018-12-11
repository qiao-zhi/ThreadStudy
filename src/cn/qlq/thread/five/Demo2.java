package cn.qlq.thread.five;

public class Demo2 {
	public static void main(String[] args) {
		final Sync2 sync2 = new Sync2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				sync2.print();
			}
		}).start();
		System.out.println("停止打印");
		sync2.setContinue(false);
	}
}

class Sync2 {
	private boolean isContinue = true;

	public boolean isContinue() {
		return isContinue;
	}

	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}

	public void print() {
		int i = 0;
		while (isContinue == true) {
			System.out.println(++i);
		}
	}
}
