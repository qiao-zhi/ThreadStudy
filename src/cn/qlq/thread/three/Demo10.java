package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * stop暴力停止释放锁造成的数据不一致
 * 
 * @author Administrator
 *
 */
public class Demo10 extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Demo10.class);
	private SyncObj syncObj;

	public Demo10(SyncObj syncObj) {
		this.syncObj = syncObj;
	}

	@Override
	public void run() {
		syncObj.setValue("b", "bbb");
	}

	public static void main(String[] args) throws InterruptedException {
		SyncObj syncObj = new SyncObj();
		Demo10 demo9 = new Demo10(syncObj);
		demo9.start();
		Thread.sleep(5 * 1000);
		// 暴力停止
		demo9.stop();
		log.debug("syncObj - > {}", syncObj);
	}
}

class SyncObj {
	private String username = "a";
	private String password = "aaa";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public synchronized void setValue(String username, String password) {
		try {
			this.username = username;
			// 休眠10秒中
			Thread.sleep(10 * 1000);
			this.password = password;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "SyncObj [username=" + username + ", password=" + password + "]";
	}
}
