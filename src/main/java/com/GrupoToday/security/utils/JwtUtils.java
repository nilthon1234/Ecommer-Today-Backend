package com.GrupoToday.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtUtils {
    // se declara la variable
    private SecretKey key;
    private static final long EXPIRATION_TIME = 86400000;//24 hrs
    public JwtUtils(){
        //Inicializacion de las variables
        String secreteString = "24234J3567655OIO5876I8JIKJKJK8J7KJ9H5VG5VG4VG53FG4VH42H4B3JH5JK4KJJRIJ4IJ4";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        //atributo de instancia
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }
    public String generateToken(UserDetails userDetails) {
        // constructor
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

    }
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                // se establece las reclamaciones
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //firma del token
                .signWith(key)
                .compact();
    }
    //extrae nombre y reclamos
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction
                //parser -> para analaizar
                .apply(Jwts.parser()
                                .verifyWith(key)
                                .build()
                                //analiza los reclamos firmados
                                .parseSignedClaims(token)
                                .getPayload()
                        );
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}

