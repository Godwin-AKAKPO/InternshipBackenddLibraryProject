package com.example.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
