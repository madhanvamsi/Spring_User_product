package org.jsp.SpringTest.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.SpringTest.UserConfig;
import org.jsp.SpringTest.dao.ProductDao;
import org.jsp.SpringTest.dao.UserDao;
import org.jsp.SpringTest.dto.Product;
import org.jsp.SpringTest.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {

	
	static Scanner s = new Scanner(System.in);
	static UserDao uDao;
	static ProductDao pDao;
	static {
		ApplicationContext c = new AnnotationConfigApplicationContext(UserConfig.class);
		uDao = c.getBean(UserDao.class);
		pDao = c.getBean(ProductDao.class);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("1.Enter to save the user");
		System.out.println("2.Enter to Update the user");
		System.out.println("3.Enter to save the Product");
		System.out.println("4.Enter to fetch the Product by User id");
		System.out.println("5.Enter to fetch the User by Phone and Password");
		System.out.println("6.Enter to fetch the User by Email and Password");
        System.out.println("7. Enter the category to fetch Product");
		int choice = s.nextInt();
		switch(choice) {
		case 1:{
			saveUser();
			break;
		}
		case 2:{
			UpdateUser();
			break;
		}
		case 3:{
			SaveProduct();
			break;
		}
		case 4:{
			fetchProductByUserId();
			break;
		}
		case 5:{
			verifyUserByPhoneAndPassword();
			break;
		}
		case 6:{
			verifyUserByEmailAndPassword();
			break;
		}
		case 7:{
			fetchProductByCategory();
			break;
		}
		}

	}
	public static void fetchProductByCategory() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the category");
		String category = s.next();
		List<Product>p = pDao.fetchProductByCategory(category);
		for(Product pro : p) {
			System.out.println(p);
		}
		
	}
	private static void verifyUserByEmailAndPassword() {
		// TODO Auto-generated method stub
		System.out.println("Enter the email and password");
		String email = s.next();
		String password = s.next();
		User u = uDao.verifyUserByEmailAndPassword(email, password);
        if(u!=null) {
        	System.out.println("name :"+u.getName());
        }
		
	}
	public static void verifyUserByPhoneAndPassword() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the Phone and password");
		long phone = s.nextLong();
		String password = s.next();
		User u = uDao.verifyUserByPhoneAndPassword(phone, password);
        if(u!=null) {
        	System.out.println("name :"+u.getName());
        }
	}
	public static void fetchProductByUserId() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the UserId");
		int uid = s.nextInt();
		List<Product> p = pDao.fetchProductByUserId(uid);
		for(Product pro :p) {
			System.out.println(pro);
		}
		
	}
	public static void SaveProduct() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the UserId");
		int uid = s.nextInt();
		User u = uDao.fetchUserById(uid);
		if(u!=null) {
			System.out.println("Enter the name, brand, description, category,price");
			String name = s.next();
			String brand = s.next();
			String description = s.next();
			String category = s.next();
			double price = s.nextDouble();
			Product p = new Product();
			p.setBrand(brand);
			p.setCategory(category);
			p.setDescription(description);
			p.setName(name);
			p.setPrice(price);
			p = pDao.saveProduct(p, uid);
			System.out.println("Product saved with th id :"+p.getId());
		}
		else {
			System.err.println("invalid id");
		}
		
	}
	public static void saveUser() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the name, email, phone and password");
		String name = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();
		User u = new User();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u = uDao.SaveUser(u);
		System.out.println("user is saved with id : "+u.getId());
		
	}
	
	public static void UpdateUser() {
		// TODO Auto-generated method stub
		System.out.println("Enter the id");
		int id = s.nextInt();
		User u = uDao.fetchUserById(id);
		if(u!=null) {
			System.out.println("Enter the phone and password,name,email");
			long phone = s.nextLong();
			String password = s.next();
			String name=s.next();
			String email=s.next();
			u.setEmail(email);
			u.setName(name);
			u.setPassword(password);
			u.setPhone(phone);
			u = uDao.UpdateUser(u);
			System.out.println("user is updated with id : "+u.getId());
		}
		
	}
	
	

}