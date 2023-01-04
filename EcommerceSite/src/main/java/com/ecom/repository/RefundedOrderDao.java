package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.RefundedOrder;

@Repository
public interface RefundedOrderDao extends JpaRepository<RefundedOrder, Integer> {

}
