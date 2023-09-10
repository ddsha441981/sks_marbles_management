package com.cwc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "category", schema = "marble_inventory_database")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name = "categoryname")
	@NotNull(message = "category name is required")
	@Min(value=2, message="category:  min 2 word is required")
	private String categoryname;
	
	@Column(name = "categoryCode")
	@NotNull(message = "category Code is required")
	private String categoryCode;
	
	@Column(name = "category_image")
	private String categoryImage;
	
	@Column(name = "category_description")
	private String categorydescription;
	
	@Column(name = "status")
	private Status status;
}
