package com.cwc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@ToString
@Entity
@Table(name = "purchase", schema = "inventory_database")
public class Purchase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1583896542698294349L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchaseId;
	private LocalDate purchasedDate;
	private double purchasedQuantity;
	private double pricePerUnit;
	@Column(precision = 10, scale = 2)
	private BigDecimal amount;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private LocalDate createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDate updatedAt;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "tax_id", nullable = false)
	private Tax tax;

//	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
//	private List<Supplier> suppliers = new ArrayList<>();

    
    @ManyToOne
    @JoinColumn(name = "supplier_id",nullable = false)
    private Supplier supplier;

}
