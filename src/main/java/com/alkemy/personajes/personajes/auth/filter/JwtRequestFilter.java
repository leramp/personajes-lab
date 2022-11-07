package com.alkemy.personajes.personajes.auth.filter;

import com.alkemy.personajes.personajes.auth.service.JwtUtils;
import com.alkemy.personajes.personajes.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {//extiende de OPR porque lo voy a ejecutar cada vez que llegue un request
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override//aca sobrescribo qué debe hacer el filtro cuando se ejecuta
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException{
        final String authorizationHeader =  request.getHeader("Authorization");//el request que llegua el pido el header
        String username = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){//Bearer es el tipo de token
            jwt = authorizationHeader.substring(7);//Bearer eyasdnaksjnda....
            //esto lo hago para obtener el jwt real y para eso le saco la parte que dice Bearer para obtener solo el token
            username = jwtUtil.extractUsername(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){
        //que no nulo y que todavia no exista autenticacio para este usuario
            UserDetails userDetails = this.userDetailsCustomService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {//esto es porque necesito verificar. sin son correctas las vaclidacion
                //creo este objeto que viene por defecto en spring y le paso el user y el password. una ves hecho eso creo
                //un authentication propiamente y le digo al manager authenticate(authReq)
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                Authentication auth = authenticationManager.authenticate(authReq);
                //Set auth in context. por ultimo le setteo la autenticacion al contexto
                SecurityContextHolder.getContext().setAuthentication(auth);
//esto es para que la proxima vez no tenga que pasar porla validacio de este filtro
            }
        }
        chain.doFilter(request, response);
        //para finalizar le decimos que el filtro sea evaluado con cain.doFilter
    }
}
