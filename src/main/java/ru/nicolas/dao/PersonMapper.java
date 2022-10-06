package ru.nicolas.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.nicolas.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
//        person.setEmail(resultSet.getString("email"));
        person.setBorn(resultSet.getInt("born"));
        return person;
    }
}
