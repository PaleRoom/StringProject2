package ru.nicolas.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nicolas.models.Book;
import ru.nicolas.models.Person;


import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    //private final JdbcTemplate jdbcTemplate;


    // public BookDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;    }
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> index() {

        Session session = sessionFactory.getCurrentSession();

        List<Book> books = session.createQuery("select p from Book p", Book.class)
                .getResultList();

        return books;

    }

    public Book show(int id) {
        return null;

    }
    public Optional<Book> show(String title) {
        return null;
    }

    public void save(Book book) {


    }

    public void update(int id, Book updatedBook) {


    }

    public void delete(int id) {

    }



    }


