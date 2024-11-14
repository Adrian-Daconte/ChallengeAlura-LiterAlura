package com.alura.literatura.dto;

public class AuthorDTO {
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    public AuthorDTO(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Getters
    public String getName() { return name; }
    public Integer getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }

    @Override
    public String toString() {
        return "AuthorDTO [name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear
                + "]";
    }

    
}
