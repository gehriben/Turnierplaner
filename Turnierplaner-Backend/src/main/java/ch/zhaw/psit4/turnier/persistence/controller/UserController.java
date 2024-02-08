package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.User;
import ch.zhaw.psit4.turnier.persistence.security.UserExistsException;
import ch.zhaw.psit4.turnier.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUserAccount(@RequestBody User user) throws UserExistsException {
        userService.createUser(user);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
