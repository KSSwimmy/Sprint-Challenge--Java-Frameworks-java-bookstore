package com.lambdaschool.bookstore;

import com.lambdaschool.bookstore.models.*;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import com.lambdaschool.bookstore.repository.BookRepository;
import com.lambdaschool.bookstore.repository.RoleRepository;
import com.lambdaschool.bookstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    BookRepository bookRepository;
    AuthorRepository authorRepository;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, BookRepository bookRepository, AuthorRepository authorRepository)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = rolerepos.save(r1);
        r2 = rolerepos.save(r2);
        r3 = rolerepos.save(r3);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> data = new ArrayList<>();
        data.add(new UserRoles(new User(), r2));
        data.add(new UserRoles(new User(), r3));


        User u1 = new User("barnbarn", "ILuvM4th!", users);

        User u2 = new User("admin", "password", admins);

        User u3 = new User("cinnamon", "123456", data);

        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);

        Book b1 = new Book("Test book", "123456789", 2018, new ArrayList<>());

        ArrayList<Book> b1List = new ArrayList<>();
        b1List.add(b1);

        Author a1 = new Author("Andrew", "Safran", new ArrayList<>());

        //Turn off to test assign book to author
        ArrayList<Author> a1List = new ArrayList<>();
        a1List.add(a1);
        b1.setAuthors(a1List);
        a1.setBooks(b1List);

        bookRepository.save(b1);
        authorRepository.save(a1);
    }
}