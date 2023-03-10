package com.learnjwt.jwt_learn.Configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learnjwt.jwt_learn.Service.JwtService;
import com.learnjwt.jwt_learn.Util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    public static String CURENT_USER = "";
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired 
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        final String header = request.getHeader("Authorization");

        String jwtToken = null ;
        String userName = null;

        if(header != null && header.startsWith("Bearer ")){
            jwtToken = header.substring(7);

            try {
                userName = jwtUtil.getUserNameFromToken(jwtToken);
                CURENT_USER= userName;
            } catch (IllegalArgumentException e) {
                System.out.println("Unabel to get JWT token");
            } catch (ExpiredJwtException e){
                System.out.println("JWT token is expired");
            }
        }   else{
                System.out.println("JWT token does not start with Bearer");
        }

        if(userName !=null && SecurityContextHolder.getContext().getAuthentication() == null ){
           UserDetails userDetails= jwtService.loadUserByUsername(userName);

           if(jwtUtil.validateToken(jwtToken,userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, 
              null, userDetails.getAuthorities());
              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }

        filterChain.doFilter(request, response);
    }
    
}
