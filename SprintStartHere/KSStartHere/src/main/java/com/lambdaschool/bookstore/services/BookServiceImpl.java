package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks(Pageable pageable)
    {
        List<Book> rtnList = new ArrayList<>();

        bookRepository.findAll(pageable).iterator().forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Transactional
    @Override
    public Book updateBook(long id, Book book) throws ResourceNotFoundException
    {
        if(bookRepository.findById(id).isPresent())
        {
            Book existingBook = bookRepository.findByBookid(id);

            if(book.getBooktitle() == null)
            {
                book.setBooktitle(existingBook.getBooktitle());
            }
            if(book.getIsbn() == null)
            {
                book.setIsbn(existingBook.getIsbn());
            }
            if(book.getCopy() == 0) //int will be zero instead of null if not initialized
            {
                book.setCopy(existingBook.getCopy());
            }

            bookRepository.updateBook(id, book.getBooktitle(), book.getIsbn(), book.getCopy());
        }else
        {
            throw new ResourceNotFoundException("Could not find book with id: " + id);
        }

        return book;

    }

    @Transactional
    @Override
    public void deleteBook(long id) throws ResourceNotFoundException
    {
        if(bookRepository.findById(id).isPresent())
        {
            if(bookRepository.findByBookid(id).getAuthors().size() != 0)
            {
                bookRepository.deleteBookFromAuthorList(id);
            }

            bookRepository.deleteById(id);

        } else
        {
            throw new ResourceNotFoundException("Could not find book with Id: " + id);
        }
    }
}