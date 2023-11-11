package com.DataProvider.DataProvider.config;


import com.DataProvider.DataProvider.Entity.User;
import com.DataProvider.DataProvider.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class jwtService {
    UserDetails userDetails;
    @Autowired
    UserRepository userRepository;
    private static final String SECURITY_KEY ="emTTPHMuoK0eBWQpIR9sow9NnWq8wWPUt3nBP0+bDT1GVUf39VxJOXcmDsMY6/F8QsjuNUW+9+7heTBv7pK/4m4Hq1hB/AG1LEUnc4jP85RQsH83CUlBmiFscFcljkSEgkeMSD7AxSu1WBq0LE97JMSAVEVBZ0MM/lAdHn+rU5RKwt4SghMGZt7Mlxz0EYUqEGaKiHyKiOKIps1WEiTCwq+Q3mfwPl9VrjdYr5mA28UvPJbvEAlMK56wu3oAlC4FVMYRTIRr4eIf5MCp7tE9dEmR+iOrdilawMhU+87Lljh4rGpOA627AdUim1oLm/ki6WwBlj5Vu6l4gGKZhSITFP40khnIXlVPnrCm9P2bfYw=" ;
    public String 	extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }
    public Claims 	extractAllClaims (String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(SECURITY_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey()
    {
        byte[] keyBytes= Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(keyBytes);  //hmacshakey is algorithm which is use for keys
    }
    public <T>T extractClaims(String token , Function<Claims,T> claimsTFunction){
        final Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    public String generateToken(Map<String, Objects>extractClaims, UserDetails userDetails ){
        Map<String,Object> claims=new HashMap<>();
      User u= userRepository.findAllByEmail(userDetails.getUsername());


        claims.put("Employee Roles",u.getUserRole());

        return Jwts.builder()
                .setClaims(extractClaims)
                .setExpiration(new Date(System.currentTimeMillis() *1000+60))
                .setSubject(userDetails.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    public  String generateToken(UserDetails userDetails )
    {

       return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isTokenValid(String token,  UserDetails userDetails)
    {
        String usr=extractUserName(token);
        return  (usr.equals(userDetails.getUsername()) && !istokenexpired(token));
    }
    public  boolean istokenexpired(String token){
        return  extactExpiration(token).before(new Date());
    }
    public Date extactExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
}
