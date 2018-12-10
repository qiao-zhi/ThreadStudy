package cn.qlq.thread.four;

import cn.qlq.thread.four.PublicClass.PrivateClass;

public class Demo15 {
	public static void main(String[] args) {
		PublicClass publicClass = new PublicClass();
		publicClass.setUsername("qlq");
		publicClass.setPassword("111222");
		PrivateClass privateClass = new PrivateClass();
		privateClass.setAddress("address");
		privateClass.setAge("19");
		System.out.println(publicClass.getUsername() + "\t" + publicClass.getPassword());
		System.out.println(privateClass.getAge() + "\t" + privateClass.getAddress());
	}
}
