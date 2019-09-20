package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
     List<Book> getAllBooks(Pageable pageable);

     Book updateBook(long id, Book book);

    Book findBookById(long id);

     void deleteBook(long id);

     Book update(Book book, long id);

     Book save(Book book);

}