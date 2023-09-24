package com.cwc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cwc.model.Tax;

@Repository
public interface TaxRespository extends JpaRepository<Tax, Integer>{

}
