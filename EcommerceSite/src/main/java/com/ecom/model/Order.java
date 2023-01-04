package com.ecom.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name= "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;
	
	private String status;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> users;
	
}
