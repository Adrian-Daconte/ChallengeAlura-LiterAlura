package com.alura.literatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literatura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
