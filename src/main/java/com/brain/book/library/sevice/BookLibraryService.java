package com.brain.book.library.sevice;

import com.brain.book.library.dao.AuthorRepository;
import com.brain.book.library.dao.BookRepository;
import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GanreEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookLibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public void addNewBook(Book book) {
        Author author = book.getAuthor();
        if (author == null) {
            throw new RuntimeException("невозможно сохранить книгу без автоора");
        }
        if (author.getId() == null) {
            author = authorRepository.save(author);
            book.setAuthor(author);
        }
        bookRepository.save(book);
    }

    public Author findAuthorByFullName(String name, String secondName, String lastName) {
        Author author = authorRepository
                .getAuthorByNameAndSecondNameAndLastName(name, secondName, lastName)
                .orElse(null);
        if (author == null) {
            throw new RuntimeException("невозможно найти автора по данным ФИО");
        }
        return author;
    }

    public List<Book> findBooksByGanres(Set<GanreEnum> ganres){
        System.out.println("Выводим список книг по жанрам");
        return bookRepository.findBooksByGanreIn(ganres);
    }


    public List<Book> findBooksByAuthor(String name, String secondName, String lastName){
        System.out.println("Выводим список книг по автору");
        Author author=findAuthorByFullName(name, secondName, lastName);
        return bookRepository.findBookByAuthor(author);
    }

}
