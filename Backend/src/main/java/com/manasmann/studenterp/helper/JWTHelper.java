package com.manasmann.studenterp.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTHelper {

    private String SECRET_KEY = "5036102881f05e4ed72b9b277613b2b45689b79acaf76ce61a3330282852d9b679f573827f3f949bf2c70ab4f631844fd7f452f8825ce3d9f6fcf1281b70482c4f461122552688ef529e1b663196d90ab530c0cf5a255802b60c2f0c646ab02e6008d734c15a9eb4ac036048fc99f8c7ee95c9ce6db98a9916f64c324ae881d0274a0e74652ec09ae332d6265b6c048b930f074d598cbe00c998bd0953d46c4cab3bf684c0e05c89a15114ba280deac7817c0a8f5fb760bfa64c6e4d6ef9bf61f2805d97ba6248a4e773bc9044b6298a996cab45dbac096e8a7e011eb5d4475225f7a0d3ef1673afd6058d9fbfdc9c8312e9ff2f2687f6626260cff586544fd0";

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Check if token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Create token with claims
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // Validate token
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
