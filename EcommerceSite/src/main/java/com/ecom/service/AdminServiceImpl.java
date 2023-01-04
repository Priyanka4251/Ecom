package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.AdminException;
import com.ecom.exception.LoginException;
import com.ecom.exception.OrderException;
import com.ecom.exception.ProductException;
import com.ecom.exception.RefundedOrderException;
import com.ecom.exception.UserException;
import com.ecom.model.Admin;
import com.ecom.model.CurrentUser;
import com.ecom.model.Order;
import com.ecom.model.Product;
import com.ecom.model.RefundedOrder;
import com.ecom.model.User;
import com.ecom.model.UserDTO;
import com.ecom.repository.AdminDao;
import com.ecom.repository.CurrentUserDao;
import com.ecom.repository.OrderDao;
import com.ecom.repository.ProductDao;
import com.ecom.repository.RefundedOrderDao;
import com.ecom.repository.UserDao;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adao;
	
	@Autowired
	private CurrentUserDao cudao;
	
	@Autowired
	private ProductDao pdao;
	
	@Autowired
	private OrderDao odao;
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private RefundedOrderDao rodao;

	

	@Override
	public Admin registerUser(Admin user) throws AdminException, LoginException {
        Admin a=adao.save(user);
		
		return a;
	}

	@Override
	public Product addProducts(Product product ,String key) throws AdminException, LoginException {

		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Admin> a= adao.findById(cu.getUserId());
		if(a.isPresent()) {
			return pdao.save(product);
		}
		throw new AdminException("You are not an admin");
	}

	@Override
	public Integer GetQuantity(String key) throws AdminException, LoginException, ProductException {
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Admin> a= adao.findById(cu.getUserId());
		if(a.isPresent()) {
			List<Product> products =pdao.findAll();
			if(products.size()==0) {
				throw new ProductException("No product is present yet");
			}
			return products.size();
		}
		throw new AdminException("You are not an admin");
		
	}
	@Override
	public List<Order> GetAllOrders(String key) throws AdminException, LoginException, OrderException {
		
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Admin> a= adao.findById(cu.getUserId());
		if(a.isPresent()) {
			List<Order> orders =odao.findAll();
			if(orders.size()==0) {
				throw new OrderException("No order yet!");
			}
		   return orders;
		}
		throw new AdminException("You are not an admin");
		
	}

	@Override
	public Order updateOrderStatus(String uuid) throws AdminException, LoginException, OrderException {
		
		
		return null;
	}

	@Override
	public List<Order> GetOrdersByUser(String key, UserDTO dto)throws AdminException, LoginException, OrderException {
		
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Admin> a= adao.findById(cu.getUserId());
		if(a.isPresent()) {
		Optional<User> user= Optional.of(udao.findByContactAndEmailAndUserLoginId(dto.getPhone(),dto.getEmail(), dto.getId()));
			if(user.isEmpty()) {
				throw new OrderException("No user is present withn this credentials!");
			}
			List<Order> orders = user.get().getOrders();
			if(orders.size()==0) {
				throw new OrderException("No order yet from this user!");
			}
		   return orders;
		}
		throw new AdminException("You are not an admin");
	}

	@Override
	public List<User> GetAllUsersByOrder(String key, Order order)throws AdminException, LoginException, OrderException {
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Admin> a= adao.findById(cu.getUserId());
		if(a.isPresent()) {
		List<User> users= order.getUsers();
			if(users.size()==0) {
				throw new OrderException("No user order this product");
			}
		   return users;
		}
		throw new AdminException("You are not an admin");
		
	}

	@Override
	public List<RefundedOrder> getAllRefundedOrders(String key) throws AdminException, LoginException, RefundedOrderException{
        
		CurrentUser cu=cudao.findByUuid(key);
		if(cu == null) {
			throw new LoginException("Login first");
		}
		Optional<Admin> a= adao.findById(cu.getUserId());
		if(a.isPresent()) {
		List<RefundedOrder> ro=rodao.findAll();
			if(ro.size()==0) {
				throw new RefundedOrderException("No refund yet!");
				
			}
		   return ro;
		}
		throw new AdminException("You are not an admin");
	}
	
	
}
