package com.sunbeam;

import java.io.Serializable;

public class Restaurant implements Serializable{

	    private static final long serialVersionUID = 1L;

	    private int id;
	    private String name, email, password, address;

	    public Restaurant() {}
	    public Restaurant(int id, String name, String email, String password, String address) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.address = address;
	    }

	    public int getId() { return id; }
	    public String getName() { return name; }
	    public String getEmail() { return email; }
	    public String getPassword() { return password; }
	    public String getAddress() { return address; }

	    public void setId(int id) { this.id = id; }
	    public void setName(String name) { this.name = name; }
	    public void setEmail(String email) { this.email = email; }
	    public void setPassword(String password) { this.password = password; }
	    public void setAddress(String address) { this.address = address; }

	    @Override
	    public String toString() {
	        return "Restaurant [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
	    }
	}

