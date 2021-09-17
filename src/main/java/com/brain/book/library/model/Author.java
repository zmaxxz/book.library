package com.brain.book.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity // анатация для превращения класса в JPA Entity
@Table // если имя явно не указано будет трансформировано название класса
@NoArgsConstructor
@Data
public class Author {

    @Id // первичный ключ таблицы
    @GeneratedValue(strategy = GenerationType.IDENTITY) // итератор для значения Id
    private Long id;

    @Column(name = "name", nullable = false) // привязка поля к колонке таблицы
    private String name;

    @Column(name = "second_name", nullable = false) // привязка поля к колонке таблицы
    private String secondName;

    @Column(name = "last_name", nullable = false) // привязка поля к колонке таблицы
    private String lastName;

    @Column(name = "birth_date", nullable = false) // привязка поля к колонке таблицы
    private LocalDate birthDate;
}
