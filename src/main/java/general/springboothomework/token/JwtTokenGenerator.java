package general.springboothomework.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenGenerator {

    public static final String SHA_256 = "54a801af8dea9faea58742902d2e5ece7b449960d5c2f7ca767160b720673978";

    public String generateToken(@NonNull String secretKey) {
        return Jwts.builder()
                .setSubject(secretKey)
                .setIssuedAt(new Date())
                .setIssuer("https://online.pdp.uz")
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(signKey(), SignatureAlgorithm.HS256) // ESDAN CHIQMASIN: HS256 ishlatyapmiz
                .compact();
    }

    public boolean validateToken(@NonNull String token) {
        try {
            Claims claims = getClaims(token);
            Date date = claims.getExpiration();
            return !date.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserName(@NonNull String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Key signKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SHA_256);
        return Keys.hmacShaKeyFor(keyBytes); // HMAC uchun mos kalit
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
