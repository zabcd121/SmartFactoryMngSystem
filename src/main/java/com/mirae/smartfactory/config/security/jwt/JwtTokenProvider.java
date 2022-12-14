package com.mirae.smartfactory.config.security.jwt;

import com.mirae.smartfactory.application.service.MemberDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {
    private final MemberDetailsService memberDetailsService;

    private final String secretKey;
    private final long tokenValidityInMs;
    private final long refreshTokenValidityInMs;

    private Key key;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.token-validity-in-sec}") long tokenValidity,
                            @Value("${jwt.refresh-token-validity-in-sec}") long refreshTokenValidity,
                            MemberDetailsService memberDetailsService){
        this.secretKey = secretKey;
        this.tokenValidityInMs = tokenValidity * 200;
        this.refreshTokenValidityInMs = refreshTokenValidity * 1000;
        this.memberDetailsService = memberDetailsService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {  // init()
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now) // ????????????
                .signWith(key, SignatureAlgorithm.HS512) // ?????????
                .setExpiration(validity) // ??????
                .compact();
    }

    /**
     * ???????????? ?????? Authentication ????????? ????????????.
     * Authentication ?????? user??? ????????? ????????????.
     * UsernamePasswordAuthenticationToken ????????? Authentication??? ?????? ????????? ?????????,
     * ??????????????? UserDetails, pw, authorities ?????? ????????????
     * setAuthenticated(true)??? ??????????????? ???????????????
     * Spring-Security??? ????????? ???????????? ???????????? ?????????
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        UserDetails userDetails = memberDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    // ?????? ????????? ??????
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
//        } catch (ExpiredJwtException e){
//            // ????????? ???????????? refresh token??? ???????????? ??????
//            throw e;
//        } catch (JwtException | IllegalArgumentException e) {
//            throw e;
//        }
        } catch (SignatureException e) {
            log.error("Invalid JWT signature");
            throw e;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty.");
            throw e;
        }
    }

    public String createRefreshToken(Authentication authentication){
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Long getExpiration(String accessToken) {
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
        Long now = new Date().getTime();

        return (expiration.getTime() - now);
    }
}
