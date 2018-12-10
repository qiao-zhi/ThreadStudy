package cn.qlq.thread.four;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicClass {
	private String username;
	private String password;

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

	static class PrivateClass {
		private static final Logger LOGGER = LoggerFactory.getLogger(PrivateClass.class);
		private String address;
		private String age;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public synchronized void test1() {
			LOGGER.debug("test1 同步方法   start---,threadName->{}", Thread.currentThread().getName());
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.debug("test1 同步方法   end---,threadName->{}", Thread.currentThread().getName());
		}

		public void test2(PrivateClass privateClass) {
			synchronized (privateClass) {
				LOGGER.debug("test2 同步代码块   start---,threadName->{}", Thread.currentThread().getName());
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LOGGER.debug("test2 同步代码块   end---,threadName->{}", Thread.currentThread().getName());
			}
		}

	}
}
