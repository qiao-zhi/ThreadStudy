package cn.qlq.thread.four;

public class PublicClass2 {
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

	public class PrivateClass {
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

		public PrivateClass(String age) {
			this.age = age;
			this.address = username;
		}
	}

	public static void main(String[] args) {
		PublicClass2 publicClass2 = new PublicClass2();
		publicClass2.setUsername("sss");
		PrivateClass privateClass = publicClass2.new PrivateClass("25");
		System.out.println(privateClass.getAddress());
		System.out.println(privateClass.getAge());
	}
}
