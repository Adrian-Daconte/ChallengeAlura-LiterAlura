package com.alura.literatura.model;

public enum CategoryLanguage {
    ESPAÑOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt"),
    ITALIANO("it");

    private String code;

    private CategoryLanguage(String code) {
        this.code = code;
    }

    public static CategoryLanguage recibido(String language) {
        for (CategoryLanguage categoryLanguage : CategoryLanguage.values()) {
            if (categoryLanguage.code.equalsIgnoreCase(language)) {
                return categoryLanguage;
            }
        }

        throw new IllegalArgumentException("Idioma no reconocido: " + language);
    }

}
