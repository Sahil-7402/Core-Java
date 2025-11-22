package com.sunbeam;

import java.io.Serializable;

public class Food implements Serializable{

	    private static final long serialVersionUID = 1L;

	    private int id;
	    private String foodName;
	    private String desc;
	    private double price;
	    private Category category;
	    private boolean available;

	    public Food() {}
	    public Food(int id, String foodName, String desc, double price, Category category, boolean available) {
	        this.id = id;
	        this.foodName = foodName;
	        this.desc = desc;
	        this.price = price;
	        this.category = category;
	        this.available = available;
	    }

	    public int getId() { return id; }
	    public String getFoodName() { return foodName; }
	    public String getDesc() { return desc; }
	    public double getPrice() { return price; }
	    public Category getCategory() { return category; }
	    public boolean isAvailable() { return available; }

	    public void setId(int id) { this.id = id; }
	    public void setFoodName(String foodName) { this.foodName = foodName; }
	    public void setDesc(String desc) { this.desc = desc; }
	    public void setPrice(double price) { this.price = price; }
	    public void setCategory(Category category) { this.category = category; }
	    public void setAvailable(boolean available) { this.available = available; }

	    @Override
	    public String toString() {
	        return "Food [id=" + id + ", name=" + foodName + ", desc=" + desc + 
	               ", price=" + price + ", category=" + category + ", available=" + available + "]";
	    }
	}

