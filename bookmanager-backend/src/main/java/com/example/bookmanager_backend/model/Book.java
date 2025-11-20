package com.example.bookmanager_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter // Generuje gettery dla wszystkich pól
@Setter // Generuje settery dla wszystkich pól
@NoArgsConstructor // To samo co konstruktor - public Book() {}
@AllArgsConstructor
@Builder
@Entity
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String title;
   private String author;
   private String year;
   private String status;

}

