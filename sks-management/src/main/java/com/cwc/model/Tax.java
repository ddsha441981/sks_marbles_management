package com.cwc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cwc.model.enums.TaxType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Setter
@Getter
@Entity
@Table(name = "tax", schema = "inventory_database")
public class Tax implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8307035991589573962L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taxId;
	
	@Column(nullable = false, columnDefinition = "varchar(20) not null default 'NO_TAX'")
	@Enumerated(EnumType.STRING)
	private TaxType taxType;
	
	private double rate;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private LocalDate createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDate updatedAt;

	
	@OneToMany(mappedBy = "tax")
	@JsonIgnore
	private List<Purchase> purchases = new ArrayList<>();
}
