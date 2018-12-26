package cn.qlq.thread.thirteen;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Demo4 {
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Person> persons = new PriorityBlockingQueue<Person>(3);
		persons.put(new Person(20,"張三"));
		persons.put(new Person(22,"李四"));
		persons.put(new Person(21,"王五"));
		persons.put(new Person(18,"八卦"));
		System.out.println(persons.take());
		System.out.println(persons.take());
		System.out.println(persons.take());
		System.out.println(persons.take());
	}
}


class Person implements Comparable<Person>{
	private int age;
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Override
	public int compareTo(Person o) {//返回-1表示排在他前面，返回1表示排在他后面
		if(o.getAge() > this.getAge()  ){
			return 1;
		}else if(o.getAge() < this.getAge()){
			return -1;
		}
		return 0;
	}
}