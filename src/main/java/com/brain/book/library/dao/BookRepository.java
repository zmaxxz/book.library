package com.brain.book.library.dao;

import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GanreEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {

    // волшебный метод
    List<Book> findBookByAuthor(Author author);

    // волшебный метод
    List<Book> findBooksByGanreIn(Collection<GanreEnum> genres);
}
