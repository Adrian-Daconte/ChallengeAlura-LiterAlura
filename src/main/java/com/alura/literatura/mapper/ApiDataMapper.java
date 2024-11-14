package com.alura.literatura.mapper;

import com.alura.literatura.api.model.DataJsonRecord;
import com.alura.literatura.api.model.BooksRecord;
import com.alura.literatura.api.model.AuthorsRecord;
import com.alura.literatura.dto.BookDTO;
import com.alura.literatura.dto.AuthorDTO;
import com.alura.literatura.dto.BookDataDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ApiDataMapper {
    public static BookDataDTO mapToBookDataDTO(DataJsonRecord dataJsonRecord) {
        BookDataDTO bookDataDTO = new BookDataDTO();

        for (BooksRecord bookRecord : dataJsonRecord.books()) {
            List<AuthorDTO> authorDTOs = bookRecord.authors().stream()
                .map(ApiDataMapper::mapToAuthorDTO)
                .collect(Collectors.toList());

            BookDTO bookDTO = new BookDTO(
                bookRecord.id(),
                bookRecord.title(),
                authorDTOs,
                bookRecord.languages(),
                bookRecord.dowload_count()
            );

            bookDataDTO.addBook(bookDTO);
        }

        return bookDataDTO;
    }

    private static AuthorDTO mapToAuthorDTO(AuthorsRecord authorRecord) {
        return new AuthorDTO(
            authorRecord.name(),
            authorRecord.birth_year(),
            authorRecord.death_year()
        );
    }
}
