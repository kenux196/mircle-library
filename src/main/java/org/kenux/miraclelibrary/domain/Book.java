package org.kenux.miraclelibrary.domain;


import lombok.*;
import org.kenux.miraclelibrary.domain.enums.BookStatus;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private LocalDateTime createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }

    public void changeStatus(BookStatus status) {
        this.status = status;
    }

    public void setCreateDate(LocalDateTime createDate) {
        if (this.createDate == null) {
            this.createDate = createDate;
        }
    }
}
