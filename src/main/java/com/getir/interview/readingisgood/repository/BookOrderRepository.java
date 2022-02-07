package com.getir.interview.readingisgood.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.getir.interview.readingisgood.model.BookOrder;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
	
	@Query("select o from BookOrder o where o.orderDate between ?1 AND ?2")
	List<BookOrder> findByStartDateAndEndDate(LocalDateTime start, LocalDateTime end);
	
	@Query("select o from BookOrder o where customer.id = ?1 AND month(o.orderDate) = ?2 AND year(o.orderDate) = ?3")
	List<BookOrder> findByCustomerIdAndMonthAndYear(Long customerId, int month, int year);
}
