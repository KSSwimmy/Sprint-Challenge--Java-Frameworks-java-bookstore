package com.lambdaschool.bookstore.repository;



import com.lambdaschool.bookstore.models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{

    //    List<Book> findAll(Pageable pageable);

    Book findByBookid(long id);

    @Modifying
    @Query(value = "UPDATE books SET booktitle=:bookTitle, isbn=:isbn, copy=:copy WHERE bookid=:bookid",
           nativeQuery = true)
    void updateBook(long bookid, String bookTitle, String isbn, int copy);

    @Modifying
    @Query(value = "DELETE FROM bookauthors WHERE bookid=:bookid",
           nativeQuery = true)
    void deleteBookFromAuthorList(long bookid);
}
