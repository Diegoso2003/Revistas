/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.jwt_token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author rafael-cayax
 */
public class GeneradorToken {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateAccessToken(String userId, String rol) {
        long expirationTime = 900_000; 
        return Jwts.builder()
                .setSubject(userId) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim("rol", rol)
                .signWith(key) 
                .compact();
    }

    public String generateRefreshToken(String userId, String rol) {
        long refreshExpirationTime = 604_800_000; 
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationTime)) 
                .claim("rol", rol)
                .signWith(key)
                .compact();
    }
}
