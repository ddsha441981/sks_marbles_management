package com.cwc.model;

import java.io.Serializable;

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
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Table(name = "categories", schema = "inventory_database")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private String categoryDescription;
	
	@Column(name = "status")
	private boolean status ;
}
