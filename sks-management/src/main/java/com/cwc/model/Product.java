package com.cwc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "product", schema = "inventory_database")
public class Product implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5677861276546164715L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int productId;
	private String productName;
	private double pricePerUnit;
	private double quantity;
	
	@Column(columnDefinition = "TEXT")
	private String productDescription;
	
	@Column(nullable = false, columnDefinition = "varchar(20) not null default 'NEW'")
	@Enumerated(EnumType.STRING)
	private ProductCondition productCondition;
	
	@Column(nullable = false, columnDefinition = "varchar(20) not null default 'INACTIVE'")
	@Enumerated(EnumType.STRING)
	private Status productStatus;
	
	
	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private LocalDate createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDate updatedAt;

	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
    private List<Purchase> purchases = new ArrayList<>();
	

}
