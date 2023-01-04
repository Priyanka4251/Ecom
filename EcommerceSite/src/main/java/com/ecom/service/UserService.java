package com.ecom.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.ecom.exception.CartException;
import com.ecom.exception.LoginException;
import com.ecom.exception.OrderException;
import com.ecom.exception.ProductException;
import com.ecom.exception.UserException;
import com.ecom.model.Cart;
import com.ecom.model.Order;
import com.ecom.model.Product;
import com.ecom.model.User;

public interface UserService {
	
	public User RegisterUser(User user) throws UserException,LoginException; 
	
	public Cart addToCart(String key,Cart cart) throws UserException,LoginException, CartException;
	
	public HashMap<Cart,Integer> getCartTotalPrice(String key,User user) throws UserException,LoginException, CartException;
	
	public Product getProduct(String key,String category) throws UserException,LoginException, ProductException;

	public List<Product> SortProductByRate(String key,int rate) throws UserException,LoginException, ProductException;
	
	public String FilterProductByPrice(String key,int price) throws UserException,LoginException, ProductException;

	public List<Order> getOrderHistory(String key,User user) throws UserException,LoginException, OrderException;

}
