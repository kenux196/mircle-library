package org.kenux.miraclelibrary.web.book.dto.request;

import lombok.*;
import org.kenux.miraclelibrary.domain.book.domain.BookCategory;
import org.kenux.miraclelibrary.domain.book.domain.BookInfo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class BookAddRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String publishDate;

    @NotNull
    private BookCategory category;

    private Integer count;

    public BookInfo toEntity() {
        return BookInfo.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .publishDate(LocalDate.parse(publishDate))
                .category(category)
                .build();
    }
}