package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;

import java.util.List;

public interface BookService
{
    List<Book> getAllBooks();

    Book updateBook(Book book);
}