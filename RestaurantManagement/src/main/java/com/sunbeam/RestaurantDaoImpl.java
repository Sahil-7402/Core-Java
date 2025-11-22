package com.sunbeam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoImpl implements RestaurantDao{

	    private Connection con;

	    public RestaurantDaoImpl() throws Exception {
	        con = DBUtil.getConnection();
	    }

	    @Override
	    public void close() {
	        try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
	    }

	    @Override
	    public int addRestaurant(Restaurant r) throws Exception {
	        String sql = "INSERT INTO restaurants VALUES (?,?,?,?,?)";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, r.getId());
	            stmt.setString(2, r.getName());
	            stmt.setString(3, r.getEmail());
	            stmt.setString(4, r.getPassword());
	            stmt.setString(5, r.getAddress());
	            return stmt.executeUpdate();
	        }
	    }

	    @Override
	    public int addFood(Food f) throws Exception {
	        String sql = "INSERT INTO food (id, food_name, description, price, category, available) VALUES (?,?,?,?,?,?)";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, f.getId());
	            stmt.setString(2, f.getFoodName());
	            stmt.setString(3, f.getDesc());
	            stmt.setDouble(4, f.getPrice());
	            stmt.setString(5, f.getCategory().name());
	            stmt.setBoolean(6, f.isAvailable());
	            return stmt.executeUpdate();
	        }
	    }

	    @Override
	    public List<Food> findByCategory(Category c) throws Exception {
	        String sql = "SELECT * FROM food WHERE category = ?";
	        List<Food> list = new ArrayList<>();
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setString(1, c.name());
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                Food f = new Food(
	                    rs.getInt("id"),
	                    rs.getString("food_name"),
	                    rs.getString("description"),
	                    rs.getDouble("price"),
	                    Category.valueOf(rs.getString("category")),
	                    rs.getBoolean("available"));
	                list.add(f);
	            }
	        }
	        return list;
	    }

	    @Override
	    public int deleteRestaurant(int id) throws Exception {
	        String sql = "DELETE FROM restaurants WHERE id = ?";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            return stmt.executeUpdate();
	        }
	    }

	    @Override
	    public int updateFoodPriceIfAvailable(int id, double newPrice) throws Exception {
	        String sql = "UPDATE food SET price = ? WHERE id = ? AND available = true";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setDouble(1, newPrice);
	            stmt.setInt(2, id);
	            return stmt.executeUpdate();
	        }
	    }

	    @Override
	    public List<Food> findFoodBelow500() throws Exception {
	        String sql = "SELECT * FROM food WHERE price < 500";
	        List<Food> list = new ArrayList<>();
	        try (PreparedStatement stmt = con.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Food f = new Food(
	                    rs.getInt("id"),
	                    rs.getString("food_name"),
	                    rs.getString("description"),
	                    rs.getDouble("price"),
	                    Category.valueOf(rs.getString("category")),
	                    rs.getBoolean("available"));
	                list.add(f);
	            }
	        }
	        return list;
	    }

	    @Override
	    public int deleteUnavailableFood() throws Exception {
	        String sql = "DELETE FROM food WHERE available = false";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            return stmt.executeUpdate();
	        }
	    }
	}

