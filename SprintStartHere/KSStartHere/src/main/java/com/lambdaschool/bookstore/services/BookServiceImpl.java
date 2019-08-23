package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks()
    {
        List<Book> rtnList = new ArrayList<>();

        bookRepository.findAll().iterator().forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Override
    public Book updateBook(Book book)
    {
        return null;
    }
}