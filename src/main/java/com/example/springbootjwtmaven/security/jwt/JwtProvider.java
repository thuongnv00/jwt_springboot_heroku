package com.example.springbootjwtmaven.security.jwt;

import com.example.springbootjwtmaven.security.userprincal.UserDetailService;
import com.example.springbootjwtmaven.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "kenshin";
    private int jwtExpiration = 86400;
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration*1000))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public boolean validateToken (String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid jwt signature -> message: " + e);

        } catch (MalformedJwtException e) {
            logger.error("invalid format -> message: " + e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired jwt token -> message: " + e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupport jwt token -> Message: "+ e);
        } catch (IllegalArgumentException e) {
            logger.error("jwt claim string is empty -> message:" +e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return username;
    }
}
