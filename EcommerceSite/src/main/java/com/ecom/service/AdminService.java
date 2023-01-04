package com.ecom.service;

import java.util.List;

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

public interface AdminService {

	public Admin registerUser(Admin user) throws AdminException, LoginException; 
	
	public Product addProducts(Product product ,String key) throws AdminException, LoginException; 

	public Integer GetQuantity(String uuid) throws AdminException, LoginException, ProductException; 

	public List<Order> GetAllOrders(String uuid) throws AdminException, LoginException, OrderException; 

	public Order updateOrderStatus(String uuid) throws AdminException, LoginException, OrderException; 

	public List<Order> GetOrdersByUser(String uuid, UserDTO dto) throws AdminException, LoginException, OrderException; 

	public List<User> GetAllUsersByOrder(String uuid, Order order) throws AdminException, LoginException, OrderException;
	
	public List<RefundedOrder> getAllRefundedOrders(String uuid) throws AdminException, LoginException, RefundedOrderException;

}
