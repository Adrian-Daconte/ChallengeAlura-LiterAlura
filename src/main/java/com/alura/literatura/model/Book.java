package com.alura.literatura.model;

import java.util.List;
import java.util.stream.Collectors;

import com.alura.literatura.api.model.BooksRecord;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true, columnDefinition = "TEXT")
    private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors;
    private Integer numDowload;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private CategoryLanguage categoryLanguage;

    public Book() {
    }

    public Book(BooksRecord book) {
        this.title = book.title();
        this.authors = book.authors().stream().map(authorRecord -> new Authors(authorRecord))
                .collect(Collectors.toList());
        this.numDowload = book.dowload_count();
        this.categoryLanguage = book.languages().stream()
                .map(CategoryLanguage::receivedLanguage)
                .findFirst()
                .orElse(CategoryLanguage.DESCONOCIDO);
                System.out.println("Creando libro: " + this.title + " - Idioma: " + categoryLanguage);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public Integer getNumDowload() {
        return numDowload;
    }

    public void setNumDowload(Integer numDowload) {
        this.numDowload = numDowload;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return """
                \n-----LIBRO-----
                Title : %s
                Authors : %s
                Dowloads : %d
                Idiomas : %s
                """.formatted(title, authors, numDowload, categoryLanguage);
    }

    public CategoryLanguage getCategoryLanguage() {
        return categoryLanguage;
    }

    public void setCategoryLanguage(CategoryLanguage categoryLanguage) {
        this.categoryLanguage = categoryLanguage;
    }

}
