package com.uplooking.vo;

public class Users {
	private Integer id;//用户id
	private String username;//账户
	private String password;//密码
	private String name;//姓名
	private Integer age;//年龄
	private Character sex;//性别
	private Long phone;//电话号码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Users(Integer id, String username, String password, String name, Integer age, Character sex, Long phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
	}
	public Users() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", age="
				+ age + ", sex=" + sex + ", phone=" + phone + "]";
	}
	
}
