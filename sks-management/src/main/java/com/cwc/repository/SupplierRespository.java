package com.cwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwc.model.Supplier;

@Repository
public interface SupplierRespository extends JpaRepository<Supplier, Integer> {

}
