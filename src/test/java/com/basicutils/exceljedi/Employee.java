package com.basicutils.exceljedi;

import com.bharat.exceljedi.annotations.Column;
import com.bharat.exceljedi.annotations.Sheet;

//@Sheet(name = "Employees")
public class Employee {

	@Column(name = "Fullname")
	private String name;
	private int age;
	@Column(name = "Monthly Salary")
	private Double salary;

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
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}

}
