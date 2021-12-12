package ru.beljankin.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.beljankin.spring.model.Person;
import ru.beljankin.spring.model.Role;
import ru.beljankin.spring.service.PersonServise;
import ru.beljankin.spring.service.RoleServise;
import ru.beljankin.spring.service.UserDetailsServiceIpml;

import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final PersonServise personServise;
    private final RoleServise roleServise;
    private final UserDetailsServiceIpml userDetailsServiceIpml;

    @Autowired
    public UserController(PersonServise personServise, RoleServise roleServise,
                          UserDetailsServiceIpml userDetailsServiceIpml){
        this.personServise = personServise;
        this.roleServise = roleServise;
        this.userDetailsServiceIpml = userDetailsServiceIpml;
    }

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") int id){
        Person person = new Person();
        person = personServise.select(id);
        String userName = userDetailsServiceIpml.loadUserByUsername(person.getUsername()).getUsername();
        Set<Role> roleset = person.getRoles();
        model.addAttribute("user",personServise.select(id))
                .addAttribute("username", personServise.findByUserName(person.getUsername()))
                .addAttribute("roleSet2", roleServise.findRoleByString("ROLE_MANAGER"))
                .addAttribute("roleSet", roleset.toString())
                .addAttribute("userDelais", userName);

        return "user/index";
    }
}
