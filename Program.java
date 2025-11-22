package com.sunbeam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
	private int empid;
	private String name;
	private double salary;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empid, String name, double salary) {
		this.empid = empid;
		this.name = name;
		this.salary = salary;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (empid != other.empid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%-20d%-15s%10.2f", empid, name, salary);
	}

}

public class Program {
	private static List<Employee> empList = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public static Employee[] getEmployees() {
		Employee[] arr = new Employee[4];
		arr[0] = new Employee(3, "Aditya", 3000.00);
		arr[1] = new Employee(2, "Rahul", 4000.00);
		arr[2] = new Employee(1, "Pratik", 2000.00);
		arr[3] = new Employee(4, "Kunal", 1000.00);
		return arr;
	}

	public static void addEmployees(Employee[] e) {
		for (Employee emp : e) {
			empList.add(emp);
		}
	}

	public static void acceptRecord(int[] empid) {
		System.out.println("Enter the empid : ");
		empid[0] = sc.nextInt();
	}

	public static Employee findEmployee(int empid) {
		Employee key = new Employee();
		key.setEmpid(empid);
		int index = empList.indexOf(key);
		if (index != -1) {
			return empList.get(index);
		}
		return null;
	}

	public static void printEmployee(Employee e) {
		if (e != null) {
			System.out.println(e.toString());
		} else
			System.out.println("Employee not found");
	}

	public static boolean removeEmployee(int empid) {
		Employee key = new Employee();
		key.setEmpid(empid);
		if (empList.contains(key)) {
			empList.remove(key);
			return true;
		}
		return false;
	}

	public static void updateEmployee(int empid) {
		Employee key = new Employee();
		key.setEmpid(empid);
		int index = empList.indexOf(key);
		if (index != -1) {
			Employee emp = empList.get(index);
			System.out.println(emp.toString());
			System.out.println("Which part do you want to update");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.print("Enter the updatedId : ");
				int updatedId = sc.nextInt();
				emp.setEmpid(updatedId);
				break;
			case 2:
				System.out.print("Enter the updatedName : ");
				String updatedName = sc.next();
				emp.setName(updatedName);
				break;
			case 3:
				System.out.println("Enter the updatedSalary : ");
				double updatedSalary = sc.nextDouble();
				emp.setSalary(updatedSalary);
				break;
			}
		} else
			System.out.println("Employee not found");
	}

	public static void printEmployees() {
		empList.sort((x, y) -> Integer.compare(x.getEmpid(), y.getEmpid()));
		// empList.forEach(e -> System.out.println(e));
		empList.forEach(System.out::println); // method reference
	}

	public static void writeRecord(String path) throws Exception {
		try(FileOutputStream fout = new FileOutputStream(path)){
			try(BufferedOutputStream bout = new BufferedOutputStream(fout)){
				try(ObjectOutputStream oout = new ObjectOutputStream(bout)){
					oout.writeObject(empList);
				}//oout.close(); 
			}//bout.close(); 
		}//fout.close(); 
	}

	public static void readRecord(String path) throws Exception {
		try(FileInputStream fin = new FileInputStream(path)){
			try(BufferedInputStream bin = new BufferedInputStream(fin)){
				try(ObjectInputStream oin = new ObjectInputStream(bin)){
					empList = (List<Employee>) oin.readObject(); 
				}//oin.close(); 
			}//bin.close(); 
		}//fin.close(); 
	}

	public static int menuList() {
		System.out.println("0.Exit");
		System.out.println("1.Add Employee");
		System.out.println("2.Find Employee");
		System.out.println("3.Remove Employee");
		System.out.println("4.Update Employee");
		System.out.println("5.Print Employee[ Sorted ]");
		System.out.println("6.Write Record");
		System.out.println("7.Read Record");
		System.out.print("Enter the choice : ");
		return sc.nextInt();
	}

	public static void main(String[] args) {
		String path = "Employees.db";
		int[] empid = new int[1];
		int choice;
		while ((choice = menuList()) != 0) {
			try {
				switch (choice) {
				case 1:
					Employee[] emp = Program.getEmployees();
					Program.addEmployees(emp);
					break;
				case 2:
					Program.acceptRecord(empid);
					Employee e = Program.findEmployee(empid[0]);
					Program.printEmployee(e);
					break;
				case 3:
					Program.acceptRecord(empid);
					boolean removedStatus = Program.removeEmployee(empid[0]);
					System.out.println(removedStatus ? "Employee removed " : "Employee not found");
					break;
				case 4:
					Program.acceptRecord(empid);
					Program.updateEmployee(empid[0]);
					break;
				case 5:
					Program.printEmployees();
					break;
				case 6: 
					Program.writeRecord(path);
					break; 
				case 7: 
					Program.readRecord(path);
					Program.printEmployees();
					break; 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
