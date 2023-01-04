package com.ecom.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	
	@NotNull(message = "Please choose your payment method is online or COD")
	private boolean isOnline;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Order order;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
}
