package com.example.demo.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.JwtConfig;
import com.example.demo.entity.Client;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	
	@Autowired
	private JwtConfig jwtConfig;
	
	
	public Key getSignKey() {
		byte [] keyB = Decoders.BASE64.decode(jwtConfig.getJwtSecretKey());
		return Keys.hmacShaKeyFor(keyB);
	}
	
	
	public String bildToken(Map<String,Object> claims,Client client, Integer expiration) {
		 return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(client.toString())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + expiration))
	                .signWith(this.getSignKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
	                .compact();
	
	}
	
	public String generateAccessToken(Client c) {
		
		return this.bildToken(new HashMap<String, Object>(),c,jwtConfig.getJwtExpiredAccessToken());
	}
	
public String generateRefreshToken(Client c) {
		
		return this.bildToken(new HashMap<String, Object>(),c,jwtConfig.getJwtExpiredRefreshToken());
	}
	

}
