package ru.nicolas.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nicolas.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    //private final JdbcTemplate jdbcTemplate; //УБРАТЬ после перезаписи всего DAO


    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // public PersonDAO(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;  }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();


    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);

    }
    public Optional<Person> show(String name) {
        return null;

    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setBorn(updatedPerson.getBorn());
        personToBeUpdated.setEmail(updatedPerson.getEmail());


    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }

//    public List<Person>  showCustomer (int id) {
//        return  null;
//                //return jdbcTemplate.query("SELECT * from person JOIN book on person.person_id = book.person_id where book_id=?",new Object[]{id}, new PersonMapper());
//    }
}

