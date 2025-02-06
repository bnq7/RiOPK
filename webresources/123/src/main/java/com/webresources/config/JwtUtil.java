package com.webresources.config;
@Service
public class JwtUtil {  
    private final String SECRET_KEY = "your_secret_key";  

    public String generateToken(String username) {  
        return Jwts.builder()  
                .setSubject(username)  
                .setIssuedAt(new Date())  
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 час  
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  
                .compact();  
    }  

    public String extractUsername(String token) {  
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();  
    }  

    public boolean validateToken(String token, UserDetails userDetails) {  
        final String username = extractUsername(token);  
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  
    }  

    private boolean isTokenExpired(String token) {  
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());  
    }  
} 
