package com.example.userservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTService {
    private static final String JWT_SECRET = "somerandomsamplesecretkeyusedforsigningjwts";

    public String getUserName(String jwt) {
        return getClaim(jwt, Claims::getSubject);
    }

    public <T> T getClaim(String joseToken, Function<Claims, T> claimsResolver) {
        final Claims allClaims = getAllClaims(joseToken);
        return claimsResolver.apply(allClaims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(final Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String joseToken, UserDetails userDetails) {
        String userName = getUserName(joseToken);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(joseToken);
    }

    private boolean isTokenExpired(String joseToken) {
        return getClaim(joseToken, Claims::getExpiration)
                .toInstant().isBefore(new Date(System.currentTimeMillis()).toInstant());
    }

    public Claims getAllClaims(String joseToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(joseToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
