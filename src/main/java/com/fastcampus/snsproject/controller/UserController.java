package com.fastcampus.snsproject.controller;

import com.fastcampus.snsproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public void join() {
        userService.join();
    }


}
