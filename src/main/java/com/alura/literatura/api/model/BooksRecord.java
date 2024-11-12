package com.alura.literatura.api.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksRecord(@JsonAlias("id") int id, @JsonAlias("title") String title,
                @JsonAlias("authors") List<AuthorsRecord> authors,
                @JsonAlias("languages") List<String> languages,
                @JsonAlias("download_count") int dowload_count) {

        @Override
        public String toString() {
                return """  
                                \n-----LIBRO-----
                                Titulo : %s
                                Autor: %s
                                Idioma : %s
                                Numero de descargas: %d    
                                                                
                                """.formatted( title, authors, languages, dowload_count);
        }


}
