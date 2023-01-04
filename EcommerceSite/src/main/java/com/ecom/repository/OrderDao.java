package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Admin;
import com.ecom.model.Order;
import com.ecom.model.User;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer>{
//	public Order findByUser(User user);
	

}
