package com.cwc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cwc.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "categories", schema = " inventory_database")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8768739898756840828L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int categoryId;

	@Column(name = "category_name")
	@NotNull
	@NotEmpty(message = "category name is required")
	private String categoryName;

	@Column(name = "categoryCode")
	@NotNull
	@NotEmpty(message = "category code is required")
	private String categoryCode;

	private String categoryImage;

	@Column(name = "category_description", columnDefinition = "TEXT")
	private String categoryDescription;

	@Column(nullable = false, columnDefinition = "varchar(20) not null default 'INACTIVE'")
	@Enumerated(EnumType.STRING)
	private Status status;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private LocalDate createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDate updatedAt;


//	@Lob
////	@JsonDeserialize
//	@Column(name = "category_image", unique = false, nullable = false, length = 1000)
//	private byte[] categoryImage;
//	private String fileType;
//	
//	private String fileName;

	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	@JsonIgnore 
	private List<Product> products = new ArrayList<>();
	

}
