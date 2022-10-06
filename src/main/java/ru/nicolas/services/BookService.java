package ru.nicolas.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nicolas.models.Book;

import ru.nicolas.models.Person;
import ru.nicolas.repositories.BookRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {

        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void makeOrder(int book_id, Person person) {
        Book orderedBook = bookRepository.getOne(book_id);
        orderedBook.setOwner(person);
        orderedBook.setOrderedAt(new Date());
        bookRepository.save(orderedBook);

    }

    @Transactional
    public void closeOrder(Book book) {
        book.setOwner(null);
        book.setOrderedAt(null);
        bookRepository.save(book);
        //  jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", id);
    }

    public List<Book> showOrdered(Person person) {

        // return jdbcTemplate.query("SELECT * from book JOIN  person on person.person_id = book.person_id where person.person_id=?",new Object[]{id},
        // new BookMapper());
        return bookRepository.findByOwner(person);
    }

    public Boolean isExpired(Book book) {
        if (book.getOrderedAt() != null) {

            return ((long) new Date().getTime() - ((long) book.getOrderedAt().getTime()) > 864000000);
        } else return false;

    }
}


