package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>
{


    @Modifying
    @Query(value = "INSERT INTO bookauthors(bookid, authorid) VALUES(:bookid, :authorid)", nativeQuery = true)
    public void assignAuthorToBook(long bookid, long authorid);
}