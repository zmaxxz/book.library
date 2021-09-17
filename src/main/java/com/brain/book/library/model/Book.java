package com.brain.book.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;

@Entity //анатация для превращения класса в JPA Entity
@Table(name = "book") //привязка Entity к определенной таблице в БД
@NoArgsConstructor
@Data
public class Book implements Serializable {

    @Id // первичный ключ таблицы
    @GeneratedValue(strategy = GenerationType.IDENTITY) // итератор для значения Id
    private Long id;

    @Column(name = "name", nullable = false) // привязка поля к колонке таблицы
    private String name;

    @Column(name = "pages", nullable = false) // привязка поля к колонке таблицы
    private Integer pagesCount;

    @Column // если имя не указано ожидается трансформация 'releaseDate' -> 'release_date'
    private Year releaseDate;

    @Column
    private String booking;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "ganre", nullable = false)
    @Enumerated(EnumType.STRING)
    private GanreEnum ganre;

    // TODO позже добавим автора и жанр
}
