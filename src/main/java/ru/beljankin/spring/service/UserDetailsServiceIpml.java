package ru.beljankin.spring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.beljankin.spring.dao.PersonDAO;
import ru.beljankin.spring.model.Person;

@Service
public class UserDetailsServiceIpml implements UserDetailsService {

    private PersonDAO personDAO;


    public UserDetailsServiceIpml(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personDAO.findByUserName(s);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User %s not found!", s));
        }
        return person;
    }
}
