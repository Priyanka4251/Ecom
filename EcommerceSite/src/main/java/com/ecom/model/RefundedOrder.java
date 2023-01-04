package com.ecom.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name="RefundedOrders")
public class RefundedOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int refundId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Order order;
	
	
}
