package com.cwc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cwc.model.enums.PaymentMethod;
import com.cwc.model.enums.Status;
import com.cwc.model.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Payment {

	private int paymentId;
//    purchase
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Column(nullable = false, columnDefinition = "varchar(20) not null default 'INACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status paymentStatus;
    
    private String paymentReferenceNumber;
    
    @Column(columnDefinition = "TEXT")
    private String paymentNotes;
   
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    
    @CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private LocalDate createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDate updatedAt;
}
