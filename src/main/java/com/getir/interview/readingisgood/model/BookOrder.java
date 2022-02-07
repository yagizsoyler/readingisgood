package com.getir.interview.readingisgood.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	private BigDecimal purchasedAmount;
	private LocalDateTime orderDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="order")
    List<OrderDetail> orderDetails;
	
}
