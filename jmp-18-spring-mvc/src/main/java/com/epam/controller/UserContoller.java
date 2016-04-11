package com.epam.controller;

import com.epam.exception.NoUserException;
import com.epam.model.User;
import com.epam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserContoller {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initial(Model model) {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public
    @ResponseBody
    List getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public
    @ResponseBody
    User create(@RequestBody User u) {
        return userRepository.save(u);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    User read(@PathVariable("id") long id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    User update(@PathVariable("id") long id, @RequestBody User u) {
        if (userRepository.exists(id)) {
            return userRepository.saveAndFlush(u);
        }
        throw new NoUserException("404", "Cant' update unexisting user");
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") long id) {
        userRepository.delete(id);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
