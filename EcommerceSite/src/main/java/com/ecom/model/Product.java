package com.ecom.model;

import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name= "Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	@NotNull(message = "Please enter the Product Name")
	@Size(min = 3, message = "Please enter atleast 3 charecter")
	private String productName;
	
	@NotNull(message = "Please add product category")
	private String productCategory;
	
	@NotNull(message = "Please enter the product's price")
	private int price;
	
	@NotNull(message = "Please add the rating!")
	private int rate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Feedback> feedbacks;
	
//	@NotNull(message = "Please tell us product is available or sold out")
//	private boolean availability;
	
}
