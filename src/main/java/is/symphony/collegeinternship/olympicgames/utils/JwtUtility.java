package is.symphony.collegeinternship.olympicgames.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import is.symphony.collegeinternship.olympicgames.security.services.AdminDetailsImpl;
import is.symphony.collegeinternship.olympicgames.security.services.AthleteDetailsImpl;
import is.symphony.collegeinternship.olympicgames.security.services.VolunteerDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtility {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtility.class);


    private Key jwtSecretKey = MacProvider.generateKey(SignatureAlgorithm.HS256);
    private String jwtSecret = jwtSecretKey.getEncoded().toString();

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal;
        if (authentication.getPrincipal() instanceof AthleteDetailsImpl){
            userPrincipal = (AthleteDetailsImpl) authentication.getPrincipal();
        } else if (authentication.getPrincipal() instanceof AdminDetailsImpl){
            userPrincipal = (AdminDetailsImpl) authentication.getPrincipal();
        } else {
            userPrincipal = (VolunteerDetailsImpl) authentication.getPrincipal();
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(signatureAlgorithm, jwtSecret)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
