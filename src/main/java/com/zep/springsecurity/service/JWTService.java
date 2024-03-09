package com.zep.springsecurity.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.Signature;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.security.KeyRep.Type.SECRET;
import static javax.xml.crypto.dsig.Transform.BASE64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;

@Component
public class JWTService {
 private static final String SECRET="123456";
    public String generateToken(String username){
        Map<String,Object> claims=new HashMap<>();
       return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() +1000*60*30))
                        .signWith(getSignKey(), SignatureAlgorithm.HS256) .compact();

    }
    private  Key getSignKey(){
byte[] keyBytes= Decoders.BASE64.decode(SECRET);
return  Keys.hmacShaKeyFor(keyBytes);
    }
}
