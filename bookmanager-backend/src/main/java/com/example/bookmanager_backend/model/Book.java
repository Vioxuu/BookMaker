package com.example.bookmanager_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
   @NotBlank(message = "Tytuł nie może być pusty")
   private String title;
   @NotBlank(message = "Autor jest wymagany")
   private String author;
   @Min(1)
   @Max(2100)
   private String publicationYear;
   private String status;

}

