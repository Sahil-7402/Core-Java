package com.sunbeam;

import java.util.List;

public interface RestaurantDao extends AutoCloseable{

		
	    int addRestaurant(Restaurant r) throws Exception;
	    int addFood(Food f) throws Exception;
	    List<Food> findByCategory(Category c) throws Exception;
	    int deleteRestaurant(int id) throws Exception;
	    int updateFoodPriceIfAvailable(int id, double newPrice) throws Exception;
	    List<Food> findFoodBelow500() throws Exception;
	    int deleteUnavailableFood() throws Exception;
	}


