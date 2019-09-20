package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.services.AuthorService;
import com.lambdaschool.bookstore.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class BooksController
{
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Return a list of all books", response = Book.class, responseContainer = "list")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Select page to retrieve"),
                               @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Determine number of results per page"),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")
                       })
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> getAllBooks(@PageableDefault(page = 0, size = 5)Pageable pageable)
    {

        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }
}
