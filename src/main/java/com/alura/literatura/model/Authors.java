package com.alura.literatura.model;

import com.alura.literatura.api.model.AuthorsRecord;

public class Authors {
    private String authorName;
    private Integer dateBirthdayAuthor;
    private Integer authorDeathAuthor;

    public Authors(AuthorsRecord authorsRecord) {
        this.authorName = authorsRecord.name();
        this.dateBirthdayAuthor = authorsRecord.birth_year();
        this.authorDeathAuthor = authorsRecord.death_year();


    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getDateBirthdayAuthor() {
        return dateBirthdayAuthor;
    }

    public void setDateBirthdayAuthor(Integer dateBirthdayAuthor) {
        this.dateBirthdayAuthor = dateBirthdayAuthor;
    }

    public Integer getAuthorDeathAuthor() {
        return authorDeathAuthor;
    }

    public void setAuthorDeathAuthor(Integer authorDeathAuthor) {
        this.authorDeathAuthor = authorDeathAuthor;
    }



}
