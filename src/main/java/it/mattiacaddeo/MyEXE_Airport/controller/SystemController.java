package it.mattiacaddeo.MyEXE_Airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/airport")
public class SystemController {
	
	private record UserLoginRequest (String clientCode, String password) {}
	
	@Autowired
    private UserDetailsService userDetailsService;
	
//	@PostMapping("/login")
//	public ResponseEntity<Void> login(@RequestBody UserLoginRequest request) {
//	    // Attempt to authenticate the user
//	    Authentication authentication = authenticateUser(request.clientCode(), request.password());
//
//	    // Set the authentication object in the SecurityContext
//	    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	    // Generate a JWT token for the authenticated user
//	    String jwtToken = generateJwtToken(authentication);
//	    System.out.println(jwtToken);
//	    // Return a successful login response with the JWT token
//	    return ResponseEntity.ok()
//	            .header("Authorization", "Bearer " + jwtToken)
//	            .build();
//	}
//
//	private Authentication authenticateUser(String username, String password) {
//	    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//	    if (userDetails == null) {
//	        throw new UsernameNotFoundException("User not found: " + username);
//	    }
//
//	    // Check password using a password encoder (e.g., BCryptPasswordEncoder)
//	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//	        throw new BadCredentialsException("Invalid credentials");
//	    }
//
//	    // Create an Authentication object representing the authenticated user
//	    Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();
//	    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
//
//	    return authentication;
//	}
//
//
//	private String generateJwtToken(Authentication authentication) {
//	    // Extract user details from the Authentication object
//	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//	    // Create a Claims object containing user information
//	    Claims claims = (Claims) Jwts.claims().setSubject(userDetails.getUsername());
//
//	    // Set additional claims (optional)
//	    claims.put("authorities", authentication.getAuthorities().stream()
//	            .map(GrantedAuthority::getAuthority)
//	            .collect(Collectors.toList()));
//
//	    // Create a signing key using a secret key (should be kept secure)
//	    String signingKey = "mySecretKey";
//	    Key signingKey2 = Keys.hmacShaKeyFor(signingKey.getBytes());
//
//	    // Create a JWT token using the Claims and signing key
//	    long currentTimeMillis = System.currentTimeMillis();
//	    int jwtExpirationSeconds = 3000;
//		long jwtExpirationTimeMillis = currentTimeMillis + jwtExpirationSeconds  * 1000;
//	    Date jwtExpirationTime = new Date(jwtExpirationTimeMillis);
//
//	    JwtBuilder builder = Jwts.builder()
//	            .setClaims(claims)
//	            .setIssuedAt(new Date(currentTimeMillis))
//	            .setExpiration(jwtExpirationTime)
//	            .signWith(signingKey2);
//
//	    // Generate the compact JWT token string
//	    String jwtToken = builder.compact();
//
//	    return jwtToken;
//	}

	

}
