package com.exampleJWT.practice.auth.service;

import com.exampleJWT.practice.entity.UserEntity;
import com.exampleJWT.practice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${SECRET}")
    private String secret;

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public <T> T extractClaim(String token, Function<Claims, T>claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token.replace("{", "").replace("}","")).getBody();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //GenerateToken roles, expiration in 10 hours
    public String generateToken(Authentication authentication) {
        User user=(User) authentication.getPrincipal();
        UserEntity userEntity=userRepository.findByEmail(user.getUsername());
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("name",userEntity.getFirstName())
                .claim("lastName", userEntity.getLastName())
                .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(Charset.forName("UTF-8"))).compact();
    }

    /*The token is validated and it is not expired*/
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
