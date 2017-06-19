package vo;

public class Student {
	private String sno;
	private String name;
	private int age;
	private String sex;
	
	public Student(String sno, String name, int age, String sex) {
		super();
		this.sno = sno;
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	public Student() {
		
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
