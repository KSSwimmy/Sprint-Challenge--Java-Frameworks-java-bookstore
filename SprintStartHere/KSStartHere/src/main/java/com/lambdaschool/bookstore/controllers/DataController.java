package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.services.AuthorService;
import com.lambdaschool.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DataController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    //GET /books - returns a JSON object list of all the books and their authors.
    @GetMapping(value = "/books")
    public ResponseEntity<?> getAllBooks()
    {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }



    //GET /authors - returns a JSON object list of all the authors and their books.
    @GetMapping(value = "/authors")
    public ResponseEntity<?> getAllAuthors()
    {
        return new ResponseEntity<>(authorService.findAllAuthors(), HttpStatus.OK);
    }
}