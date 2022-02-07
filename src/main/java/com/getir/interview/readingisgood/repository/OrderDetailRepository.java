package com.getir.interview.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.interview.readingisgood.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
