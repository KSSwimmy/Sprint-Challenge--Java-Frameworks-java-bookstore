package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAllAuthors()
    {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(list::add);

        return list;

    }

    @Override
    public void assignBookToAuthor(long bookid, long authorid)
    {

    }
}
