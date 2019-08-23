package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Author;

import java.util.List;

public interface AuthorService
{
    List<Author> findAllAuthors();

    void assignBookToAuthor(long bookid, long authorid);
}