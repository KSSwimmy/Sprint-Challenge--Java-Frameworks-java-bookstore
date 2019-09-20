package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.*;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthors(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(list::add);

        return list;

    }


    @Override
    public Author findAuthorById(long id)
    {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }
}
