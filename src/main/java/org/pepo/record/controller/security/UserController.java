package org.pepo.record.controller.security;

import org.pepo.record.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('test.create')")
    @PostMapping
    public void insertUsers() {
        userService.insertUsers();
    }

}
