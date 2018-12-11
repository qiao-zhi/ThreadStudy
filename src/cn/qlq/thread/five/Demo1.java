package cn.qlq.thread.five;

public class Demo1 {
	public static void main(String[] args) {
		Sync1 sync1 = new Sync1();
		sync1.print();
		System.out.println("停止循环");
		sync1.setContinue(false);
	}
}

class Sync1 {
	private boolean isContinue = true;

	public boolean isContinue() {
		return isContinue;
	}

	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}

	public void print() {
		int i = 0;
		while (isContinue) {
			System.out.println(++i);
		}
	}
}
