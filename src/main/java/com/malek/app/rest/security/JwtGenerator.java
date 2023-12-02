package com.malek.app.rest.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtGenerator {

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        // generate  JWT token
        String token = Jwts.builder().setSubject(username)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();

        return token;
    }

     public String getUsernameFromJWT(String token) {
        Claims claims =
                Jwts.parser().
                setSigningKey(SecurityConstants.JWT_SECRET).
                parseClaimsJws(token).getBody();

        return claims.getSubject();

     }

     // validate the token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }
        catch(Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Expired or invalid JWT token");
        }
    }
}
