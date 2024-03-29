package com.egs.technicaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egs.technicaltask.model.Customer;


@Repository
public interface CustomerRespository extends JpaRepository<Customer,Long> {

}