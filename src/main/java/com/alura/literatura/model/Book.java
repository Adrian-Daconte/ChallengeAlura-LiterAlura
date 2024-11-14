package com.alura.literatura.model;

import com.alura.literatura.api.model.BooksRecord;

public class Book {

    private String title;
    private CategoryLanguage categoryLanguage;
    private Integer dowload_count;


    // constructor, getters and setters
    public Book(BooksRecord booksRecord  )  {
        this.title = booksRecord.title();
        this.dowload_count = booksRecord.dowload_count();
    }
    

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }



    public CategoryLanguage getCategoryLanguage() {
        return categoryLanguage;
    }


    public void setCategoryLanguage(CategoryLanguage categoryLanguage) {
        this.categoryLanguage = categoryLanguage;
    }


    public Integer getDowload_count() {
        return dowload_count;
    }


    public void setDowload_count(Integer dowload_count) {
        this.dowload_count = dowload_count;
    }

    


}
