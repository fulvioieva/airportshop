package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.security")
@Getter
@Setter
public class JwtConfig {
	private String jwtSecretKey;
	/**
	 * @return the jwtSecretKey
	 */
	public String getJwtSecretKey() {
		return jwtSecretKey;
	}
	/**
	 * @param jwtSecretKey the jwtSecretKey to set
	 */
	public void setJwtSecretKey(String jwtSecretKey) {
		this.jwtSecretKey = jwtSecretKey;
	}
	/**
	 * @return the jwtExpiredAccessToken
	 */
	public Integer getJwtExpiredAccessToken() {
		return jwtExpiredAccessToken;
	}
	/**
	 * @param jwtExpiredAccessToken the jwtExpiredAccessToken to set
	 */
	public void setJwtExpiredAccessToken(Integer jwtExpiredAccessToken) {
		this.jwtExpiredAccessToken = jwtExpiredAccessToken;
	}
	/**
	 * @return the jwtExpiredRefreshToken
	 */
	public Integer getJwtExpiredRefreshToken() {
		return jwtExpiredRefreshToken;
	}
	/**
	 * @param jwtExpiredRefreshToken the jwtExpiredRefreshToken to set
	 */
	public void setJwtExpiredRefreshToken(Integer jwtExpiredRefreshToken) {
		this.jwtExpiredRefreshToken = jwtExpiredRefreshToken;
	}
	private Integer jwtExpiredAccessToken;
	private Integer jwtExpiredRefreshToken;
	
	

}
