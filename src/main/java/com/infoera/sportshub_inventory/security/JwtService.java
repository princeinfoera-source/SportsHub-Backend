package com.infoera.sportshub_inventory.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    // Minimum 32 characters long secret key
    private static final String SECRET = "my_super_secret_key_1234567890_infoera_sports";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 1. Token Generate Karein
    public String generateToken(String email, String companyId) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("companyId", companyId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 Hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Token se Company ID Nikalein
    public String extractCompanyId(String token) {
        return extractClaim(token, claims -> (String) claims.get("companyId"));
    }

    // 3. Token se Email (Subject) Nikalein
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 4. Check karein ki Token Valid hai ya Expire ho gaya
    public boolean isTokenValid(String token) {
        try {
            return !extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return false; // Agar parsing fail hui toh token invalid hai
        }
    }

    // --- Private Helper Methods ---

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}