package com.brain.book.library.controller;

import com.brain.book.library.dao.AuthorRepository;
import com.brain.book.library.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors") // будем обращаться (localhost:8080/api/authors)
@AllArgsConstructor
public class AuthorRestController {

    private final AuthorRepository authorRepository;

    @GetMapping // получить список всех авторов, в будущем усложнится фильтрами и пагинацией
    public ResponseEntity<Iterable<Author>> getAuthorList(){
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}") // получить одного автора по id
    public ResponseEntity<Author> getAuthor (@PathVariable Long id){
        return new ResponseEntity<>(authorRepository.findById(id).orElse(null),HttpStatus.OK);
    }

    @DeleteMapping("{id}") // удалить одного автора по id
    public ResponseEntity<Author> removeAuthor (@PathVariable Long id){
        authorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping // сохранить нового автора
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(savedAuthor,HttpStatus.CREATED);
    }

    @PutMapping("{id}") // обновить данные автора
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author){
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(savedAuthor,HttpStatus.OK);
    }
}
