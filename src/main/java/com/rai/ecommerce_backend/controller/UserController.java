package com.rai.ecommerce_backend.controller;

import com.rai.ecommerce_backend.dto.ResponseDto;
import com.rai.ecommerce_backend.dto.user.SignInDto;
import com.rai.ecommerce_backend.dto.user.SignInResponseDto;
import com.rai.ecommerce_backend.dto.user.SignupDto;
import com.rai.ecommerce_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //signup

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.singUp(signupDto);
    }

    //signin

    @PostMapping("signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }
}
