package com.brain.book.library.controller;


import com.brain.book.library.model.Author;
import com.brain.book.library.model.Book;
import com.brain.book.library.sevice.BookLibraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books") // будем обращаться (localhost:8080/api/authors)
@AllArgsConstructor
public class BookRestController {

    private final BookLibraryService bookLibraryService;

    @GetMapping

    public ResponseEntity<Author> findBooks(@RequestParam("name") String name,
                                            @RequestParam("secondName") String secondName,
                                            @RequestParam("lastName") String lastName){
    return new ResponseEntity<>(bookLibraryService.findAuthorByFullName(name,secondName,lastName), HttpStatus.OK);
    }
}
