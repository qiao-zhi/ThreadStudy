package cn.qlq.thread.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * suspend和resume导致数据不同步
 * 
 * @author QiaoLiQiang
 * @time 2018年12月7日下午9:32:50
 */
public class Demo15 extends Thread {
	private static final Logger log = LoggerFactory.getLogger(Demo15.class);
	private SyncObj3 syncObj3;

	@Override
	public void run() {
		syncObj3.setVal("b", "bbb");// 启动线程设置值
	}

	public Demo15(String name, SyncObj3 syncObj3) {
		this.setName(name);
		this.syncObj3 = syncObj3;
	}

	public static void main(String[] args) throws InterruptedException {
		SyncObj3 syncObj3 = new SyncObj3();
		Demo15 demo15 = new Demo15("b", syncObj3);
		demo15.start();

		log.debug("main start");
		Thread.sleep(3 * 1000);
		demo15.suspend();// 暂停线程
		Thread.sleep(5 * 1000);
		log.debug("syncObj31->{}", syncObj3);

		demo15.resume();// 恢复线程
		Thread.sleep(1 * 1000);
		log.debug("syncObj32->{}", syncObj3);

		Thread.sleep(2000);
		log.debug("syncObj33->{}", syncObj3);
	}
}

class SyncObj3 {
	private String username = "a";
	private String password = "aaa";
	private static final Logger log = LoggerFactory.getLogger(SyncObj3.class);

	public void setVal(String username, String password) {
		this.username = username;

		try {
			log.debug("{}线程将要休眠5秒钟", Thread.currentThread().getName());
			Thread.sleep(5 * 1000);
			log.debug("{}线程睡醒了", Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.debug("{}线程设置密码的值,password->{}", Thread.currentThread().getName(), password);
		this.password = password;
	}

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

	@Override
	public String toString() {
		return "SyncObj3 [username=" + username + ", password=" + password + "]";
	}
}