package ru.beljankin.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.dao.PersonDAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiseImpl implements PersonServise{

    private PersonDAO personDAO;

    private BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    public PersonServiseImpl(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @Override
    public Person select(long id) {
        return personDAO.select(id);
    }

    @Override
    public void save(Person person) {
        person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));
        personDAO.save(person);
    }

    @Override
    public void delete(long id) {
        personDAO.delete(id);
    }


    @Override
    public void update(long id, Person person) {
        person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));
        personDAO.update(id, person);
    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    public Person findByUserName(String username) {
        return personDAO.findByUserName(username);
    }
}
