package com.lib.authorization.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {

	private static final String SECRET_KEY = "sec2d2a2dakd21Zaksjywqekdemo[osejoew,askjoqawqh823-3904798sdjaolsdniuanxcxuret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {

        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10))
                .signWith(SignatureAlgorithm.HS384, SECRET_KEY).compact(); //HS256 
    }
    public Boolean validateToken(String token,UserDetails userDetails) {    	
    	
    	try {	    	
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    	}
    	catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
       		return false;
        }
    	catch(Exception exception)
    	{
    		System.out.println("Expired JWT token");
    		return false;
    	}
       
    }

	public String extractempid(String token) {
		String name = (String) Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("sub");
		return name;
		
	}

}
