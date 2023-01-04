package com.ecom.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exception.AdminException;
import com.ecom.exception.LoginException;
import com.ecom.exception.OrderException;
import com.ecom.exception.ProductException;
import com.ecom.exception.RefundedOrderException;
import com.ecom.model.Admin;
import com.ecom.model.Order;
import com.ecom.model.Product;
import com.ecom.model.RefundedOrder;
import com.ecom.model.User;
import com.ecom.model.UserDTO;
import com.ecom.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> registerUser(@Valid @RequestBody Admin user) throws AdminException, LoginException {
		Admin a=aService.registerUser(user);
		
		return new ResponseEntity<Admin>(a,HttpStatus.CREATED);
	}
	
	@PostMapping("/product")
	public ResponseEntity <Product> registerBus(@Valid @RequestBody Product product, @RequestParam ("key") String key) throws AdminException, LoginException{
		
		Product p=aService.addProducts(product, key);
		
		return new ResponseEntity<Product>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/quantity")
	public ResponseEntity<Integer> getQuantity(@RequestParam ("key") String key) throws AdminException, LoginException, ProductException {
		
		Integer b=aService.GetQuantity(key);
		
		return new ResponseEntity<Integer>(b,HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrderList(@RequestParam ("key") String key) throws AdminException, LoginException, OrderException {
		
		List<Order> orders=aService.GetAllOrders(key);
		
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/ordersByUser")
	public ResponseEntity<List<Order>> getOrderList(@RequestParam ("key") String key, @RequestBody UserDTO dto ) throws AdminException, LoginException, OrderException {
		
		List<Order> orders=aService.GetOrdersByUser(key, dto);
		
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/UsersByOrder")
	public ResponseEntity<List<User>> getUserList(@RequestParam ("key") String key, @RequestBody Order order) throws AdminException, LoginException, OrderException {
		
		List<User> users=aService.GetAllUsersByOrder(key, order);
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/Refunds")
	public ResponseEntity<List<RefundedOrder>> getRefundsList(@RequestParam ("key") String key, @RequestBody Order order) throws AdminException, LoginException, RefundedOrderException {
		
		List<RefundedOrder> ro=aService.getAllRefundedOrders(key);
		
		return new ResponseEntity<List<RefundedOrder>>(ro,HttpStatus.OK);
	}
	
	
}
