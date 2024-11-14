package com.alura.literatura.dto;

import java.util.List;
public class BookDTO {
    private int id;
    private String title;
    private List<AuthorDTO> authors;
    private List<String> languages;
    private int downloadCount;

    public BookDTO(int id, String title, List<AuthorDTO> authors, List<String> languages, int downloadCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public List<AuthorDTO> getAuthors() { return authors; }
    public List<String> getLanguages() { return languages; }
    public int getDownloadCount() { return downloadCount; }
}
