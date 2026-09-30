package com.cg.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.customer.domain.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
