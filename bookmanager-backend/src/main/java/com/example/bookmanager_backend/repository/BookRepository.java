package com.example.bookmanager_backend.repository;

import com.example.bookmanager_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> { }
