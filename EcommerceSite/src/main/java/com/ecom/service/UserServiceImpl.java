package com.ecom.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.CartException;
import com.ecom.exception.LoginException;
import com.ecom.exception.OrderException;
import com.ecom.exception.ProductException;
import com.ecom.exception.UserException;
import com.ecom.model.Admin;
import com.ecom.model.Cart;
import com.ecom.model.CurrentUser;
import com.ecom.model.Order;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.model.UserDTO;
import com.ecom.repository.AdminDao;
import com.ecom.repository.CartDao;
import com.ecom.repository.CurrentUserDao;
import com.ecom.repository.OrderDao;
import com.ecom.repository.ProductDao;
import com.ecom.repository.RefundedOrderDao;
import com.ecom.repository.UserDao;

@Service
public class UserServiceImpl implements UserService  {

	
	@Autowired
	private CurrentUserDao cudao;
	
	@Autowired
	private ProductDao pdao;
	
	@Autowired
	private OrderDao odao;
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private CartDao cdao;
	
	@Autowired
	private RefundedOrderDao rodao;

	@Override
	public User RegisterUser(User user) throws UserException, LoginException {
		 
		User u=udao.save(user);
		
		new UserDTO(u.getContact(), u.getEmail(), u.getFirstName()+" "+u.getLastName(), u.getUserLoginId());
			
		return u; 
	}

	@Override
	public Cart addToCart(String key, Cart cart) throws UserException, LoginException, CartException {
		
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		
		return cdao.save(cart);
		
	}

	@Override
	public HashMap<Cart,Integer> getCartTotalPrice(String key, User user) throws UserException, LoginException, CartException {
		
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		HashMap<Cart,Integer> map=new HashMap<>();
		Cart cart=user.getCart();
		List<Product> products=cart.getProducts();
		int price=0;
		for(int i=0; i<products.size(); i++) {
			price+=products.get(i).getPrice();
		}
		map.put(cart, price);
		return map;
	}

	@Override
	public Product getProduct(String key, String category) throws UserException, LoginException, ProductException {

		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Product> opt=Optional.of(pdao.findByProductCategory(category));
		
		if(opt.isEmpty()) {
			throw new ProductException("No product listed in this category!");
		}
		
		return opt.get();
	}

	@Override
	public List<Product> SortProductByRate(String key, int rate) throws UserException, LoginException, ProductException {
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
          ArrayList<Product> products = (ArrayList<Product>) pdao.findAll();
		
		if(products.size()==0) {
			throw new ProductException("No product found!");
		}

//		products.sort((o1, o2)->o1.getRate().compareTo(o2.getRate()));
		
		return products;
	}

	@Override
	public String FilterProductByPrice(String key, int price) throws UserException, LoginException, ProductException {
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
          List<Product> products = pdao.findAll();
		
		if(products.size()==0) {
			throw new ProductException("No product found!");
		}

	   products.stream().filter(o-> o.getPrice()==price).distinct().forEach(System.out::println);
	   
	   return null;
		
	}

	@Override
	public List<Order> getOrderHistory(String key, User user) throws UserException, LoginException, OrderException {
		
		CurrentUser cu=cudao.findByUuid(key);
		
		if(cu == null) {
			throw new LoginException("Login first");
		}
		List<Order> orders=user.getOrders();
		if(orders.size()==0) {
			throw new OrderException("No order yet!");
		}
		return orders;
	} 
	
	
}
