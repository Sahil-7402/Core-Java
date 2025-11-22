package com.sunbeam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class Tester {
	    public static int menuList(Scanner sc) {
	        System.out.println("\n===== Restaurant Management =====");
	        System.out.println("1. Add Restaurant");
	        System.out.println("2. Add Food");
	        System.out.println("3. Find Food by Category");
	        System.out.println("4. Delete Restaurant");
	        System.out.println("5. Update Food Price if Available");
	        System.out.println("6. Show Food < 500");
	        System.out.println("7. Delete Unavailable Food");
	        System.out.println("8. Write All Foods to File");
	        System.out.println("9. Read All Foods from File");
	        System.out.println("0. Exit");
	        System.out.print("Enter your choice: ");
	        return sc.nextInt();
	    }

	    // 🔹 File Write using FileOutputStream → BufferedOutputStream → ObjectOutputStream
	    public static void writeFoodsToFile(List<Food> list, String filename) {
	        try (FileOutputStream fout = new FileOutputStream(filename);
	             BufferedOutputStream bout = new BufferedOutputStream(fout);
	             ObjectOutputStream oout = new ObjectOutputStream(bout)) {
	            oout.writeObject(list);
	            System.out.println(" Foods successfully written to file: " + filename);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // 🔹 File Read using FileInputStream → BufferedInputStream → ObjectInputStream
	    @SuppressWarnings("unchecked")
	    public static void readFoodsFromFile(String filename) {
	        try (FileInputStream fin = new FileInputStream(filename);
	             BufferedInputStream bin = new BufferedInputStream(fin);
	             ObjectInputStream oin = new ObjectInputStream(bin)) {
	            List<Food> list = (List<Food>) oin.readObject();
	            System.out.println("===== Foods Read From File =====");
	            for (Food f : list)
	                System.out.println(f);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        final String FILE_NAME = "foods.dat";

	        try (RestaurantDaoImpl dao = new RestaurantDaoImpl()) {
	            int choice;
	            while ((choice = menuList(sc)) != 0) {
	                switch (choice) {
	                    case 1:
	                        System.out.print("Enter ID, Name, Email, Password, Address: ");
	                        Restaurant r = new Restaurant(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next());
	                        System.out.println("Rows affected: " + dao.addRestaurant(r));
	                        break;

	                    case 2:
	                        System.out.print("Enter ID, Name, Desc, Price, Category, Available(true/false): ");
	                        Food f = new Food(sc.nextInt(), sc.next(), sc.next(), sc.nextDouble(),
	                                Category.valueOf(sc.next().toUpperCase()), sc.nextBoolean());
	                        System.out.println("Rows affected: " + dao.addFood(f));
	                        break;

	                    case 3:
	                        System.out.print("Enter Category: ");
	                        Category c = Category.valueOf(sc.next().toUpperCase());
	                        List<Food> foods = dao.findByCategory(c);
	                        foods.forEach(System.out::println);
	                        break;

	                    case 4:
	                        System.out.print("Enter Restaurant ID: ");
	                        System.out.println("Rows deleted: " + dao.deleteRestaurant(sc.nextInt()));
	                        break;

	                    case 5:
	                        System.out.print("Enter Food ID and new Price: ");
	                        System.out.println("Rows updated: " + dao.updateFoodPriceIfAvailable(sc.nextInt(), sc.nextDouble()));
	                        break;

	                    case 6:
	                        List<Food> cheapFoods = dao.findFoodBelow500();
	                        cheapFoods.forEach(System.out::println);
	                        break;

	                    case 7:
	                        System.out.println("Deleted unavailable foods: " + dao.deleteUnavailableFood());
	                        break;

	                    case 8:
	                        List<Food> all = dao.findFoodBelow500(); // example use
	                        writeFoodsToFile(all, FILE_NAME);
	                        break;

	                    case 9:
	                        readFoodsFromFile(FILE_NAME);
	                        break;

	                    default:
	                        System.out.println("Invalid choice!");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        sc.close();
	    }
	}

