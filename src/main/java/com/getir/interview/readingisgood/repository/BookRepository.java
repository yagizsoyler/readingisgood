package com.getir.interview.readingisgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.getir.interview.readingisgood.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
