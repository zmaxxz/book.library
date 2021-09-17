package com.brain.book.library;

import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.model.GanreEnum;
import com.brain.book.library.sevice.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication

public class Application {

    @Autowired
    private BookLibraryService bookLibraryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        addTestRoman();
        addTestFantasy();
        printAuthorBooks();
        printFilteredBooks();
    }

    private void addTestRoman() {
        Book book = new Book();
        book.setBooking("Подвал на улице Ленина");
        book.setName("Армено и Жилетта");
        book.setPagesCount(300);
        book.setReleaseDate(Year.of(2017));

        book.setGanre(GanreEnum.ROMAN);

        Author author = new Author();
        author.setName("Иннокентий");
        author.setSecondName("Педросович");
        author.setLastName("Пупырышкин");
        author.setBirthDate(LocalDate.of(1990, 4, 12));

        book.setAuthor(author);
        bookLibraryService.addNewBook(book);

    }

    private void addTestFantasy() {
        Book book = new Book();
        book.setBooking("Крыша на улице чикистов");
        book.setName("Пластелин колец");
        book.setPagesCount(800);
        book.setReleaseDate(Year.of(2010));

        book.setGanre(GanreEnum.FANTAZY);

        Author author = bookLibraryService
                .findAuthorByFullName("Иннокентий", "Педросович", "Пупырышкин");
        book.setAuthor(author);
        bookLibraryService.addNewBook(book);
    }

    private void printAuthorBooks() {
        List<Book> books = bookLibraryService
                .findBooksByAuthor("Иннокентий", "Педросович", "Пупырышкин");
        books.forEach(System.out::println);
    }

    private void printFilteredBooks() {
        Set<GanreEnum> genres = new HashSet<>();
        genres.add(GanreEnum.ROMAN);
        genres.add(GanreEnum.FANTAZY);

        List<Book> books = bookLibraryService
                .findBooksByGanres(genres);
        books.forEach(System.out::println);
    }
}
