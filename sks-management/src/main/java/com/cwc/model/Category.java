package com.cwc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "categories", schema = "inventory_database")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name = "category_name")
	@NotNull
	@NotEmpty(message="category name is required")
	private String categoryName;
	
	@Column(name = "categoryCode")
	@NotNull
	@NotEmpty(message="category code is required")
	private String categoryCode;
	
	@Column(name = "category_image")
	private String categoryImage;
	
	@Column(name = "category_description")
	private String categorydescription;
	
	@Column(name = "status")
	private Status status;
}
