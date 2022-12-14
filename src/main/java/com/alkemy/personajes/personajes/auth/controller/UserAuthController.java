package com.alkemy.personajes.personajes.auth.controller;

import com.alkemy.personajes.personajes.auth.dto.AuthenticationRequest;
import com.alkemy.personajes.personajes.auth.dto.AuthenticationResponse;
import com.alkemy.personajes.personajes.auth.dto.UserDTO;
import com.alkemy.personajes.personajes.auth.service.JwtUtils;
import com.alkemy.personajes.personajes.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")//desde security le dijimos que to do lo que tenga auth lo deje pasar
public class UserAuthController {//se usa para la registracion (signup) y el login(signin)
    private UserDetailsCustomService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    @Autowired
    public UserAuthController(
            UserDetailsCustomService userDetailsService,
            AuthenticationManager authenticationManager,
            JwtUtils jwtTokenUtil){
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponse> signup(@Valid @RequestBody UserDTO user)throws Exception {
        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authRequest) throws Exception{
        UserDetails userDetails;
        try{//esto del signin deberia hacerlo el servicio
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

