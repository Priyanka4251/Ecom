package com.ecom.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exception.AdminException;
import com.ecom.exception.CartException;
import com.ecom.exception.LoginException;
import com.ecom.exception.OrderException;
import com.ecom.exception.ProductException;
import com.ecom.exception.UserException;
import com.ecom.model.Cart;
import com.ecom.model.Order;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/user")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserException, LoginException {
		User u = uService.RegisterUser(user);
		
		return new ResponseEntity<User>(u,HttpStatus.CREATED);
	}
	
	@PostMapping("/cart")
	public ResponseEntity <Cart> AddCart(@Valid @RequestBody Cart cart, @RequestParam ("key") String key) throws UserException, LoginException, CartException{
		
		Cart c=uService.addToCart(key,cart);
		
		return new ResponseEntity<Cart>(c,HttpStatus.CREATED);
	}
	
	@GetMapping("/cart")
	public ResponseEntity <HashMap<Cart,Integer>> getCartAndTotalPrice(@Valid @RequestBody User user, @RequestParam ("key") String key) throws UserException, LoginException, CartException{
		
		HashMap<Cart,Integer> map=uService.getCartTotalPrice(key, user);
				
		return new ResponseEntity<HashMap<Cart,Integer>>(map,HttpStatus.OK);
	}
	@GetMapping("/product/{c}")
	public ResponseEntity <Product> getProduct(@Valid @RequestBody User user, @PathVariable("c") String category, @RequestParam ("key") String key) throws UserException, LoginException, ProductException{
		
		Product p=uService.getProduct(key, category);			
		return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping("/sort/{rate}")
	public ResponseEntity <List<Product>> SortProductByRating( @PathVariable("rate") int rate, @RequestParam ("key") String key) throws UserException, LoginException, ProductException{
		
		List<Product> p=uService.SortProductByRate(key, rate);
				return new ResponseEntity<List<Product>>(p,HttpStatus.OK);
	}
	
	@GetMapping("/filter/{p}")
	public ResponseEntity <String> Filter( @PathVariable("p") int price, @RequestParam ("key") String key) throws UserException, LoginException, ProductException{
		
		       uService.SortProductByRate(key, price);
		       String p="";
				return new ResponseEntity<String>(p,HttpStatus.OK);
	}
	@GetMapping("/GetOrders")
	public ResponseEntity <List<Order>> getOrdersbyUsers( @RequestBody User user, @RequestParam ("key") String key) throws UserException, LoginException, OrderException{
		
		        List<Order> orders=uService.getOrderHistory(key, user);
				return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	
}
