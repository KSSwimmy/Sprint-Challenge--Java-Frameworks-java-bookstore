package com.lambdaschool.bookstore.controllers;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
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

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class DataController
{
    @Autowired private AuthorService authorService;

    @Autowired private BookService bookService;


    @ApiOperation(value = "Return a list of all authors", response = Book.class, responseContainer = "list")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Select page to retrieve"),
                               @ApiImplicitParam(name = "size", dataType = "Integer", paramType = "query", value = "Determine number of results per page"),
                               @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                                                 value = "Sorting criteria in the format: property(,asc|desc). " +
                                                         "Default sort order is ascending. " +
                                                         "Multiple sort criteria are supported.")
                       })
    @GetMapping(value = "/authors",
                produces = {"application/json"})
    public ResponseEntity<?> getAllAuthors(@PageableDefault(page = 0,
                                                            size = 5)Pageable pageable)
    {
        List<Author> myAuthor = authorService.findAllAuthors(pageable);
        return new ResponseEntity<>(myAuthor, HttpStatus.OK);
    }

    //UpdatesBook ////////////////////////////////////////////////////////////////
    @ApiOperation(value = "Updates data for a given book, excluding author", notes = "The updated book will be returned", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book updated successfully", response = Book.class),
            @ApiResponse(code = 404, message = "Book to update not found in system", response = ResourceNotFoundException.class)
    })
    @PutMapping(value = "/books/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateBook(@ApiParam(name = "Book Id", required = true) @PathVariable long id, @RequestBody @Valid Book book)
    {
        bookService.updateBook(id, book);
        return new ResponseEntity<>(HttpStatus.OK);
    }






    @ApiOperation(value = "assigns a book to the specified author", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully assigned book to author", response = void.class),
            @ApiResponse(code = 404, message = "Selected book or author could not be found", response = ResourceNotFoundException.class)
    })
    @PostMapping(value = "/books/authors/{id}")
    public ResponseEntity<?> assignBookToAuthor(@PathVariable long id, @RequestBody long bookId)
    {
        Book book = bookService.findBookById(id);
        Author author = authorService.findAuthorById(id);

        book.getAuthors().add(author);
        Book b = bookService.save(book);

        return new ResponseEntity<>(b, HttpStatus.CREATED);
    }






    @ApiOperation(value = "Deletes specified book from records", notes = "This will also delete the book from all authors it was assigned to, but will not delete authors", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted book", response = void.class),
            @ApiResponse(code = 404, message = "Could not find selected book to delete", response = ResourceNotFoundException.class)
    })
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}