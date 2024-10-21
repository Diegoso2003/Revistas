/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.jwt_token;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rafael-cayax
 */
public class TokenJWT {
    private final String claveSecreta = "3qyvyAaBF/u7u51TxSUJvevCmXly4/X0pmY/H6cvq4c=";
    private final SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(claveSecreta), SignatureAlgorithm.HS256.getJcaName());

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
    
    private Claims decodeJWT(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
    
    public String obtenerNombreUsuario(String token) throws DatosUsuarioException{
        if (token == null || !token.startsWith("Bearer")) {
            throw new DatosUsuarioException("token no valido");
        }
        Claims datos = decodeJWT(token.substring(7));
        return datos.getSubject();
    }
}
