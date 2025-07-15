package dev.hisham.universal_clipboard.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
@Value("${REMOVED}")
    private static final String REMOVED;
public String extarctUsername (String jwtToken){

return extractClaims(jwtToken,Claims::getSubject);
}
public String generateJwtToken(UserDetails user){
    return generateJwtToken(new HashMap<>(),user);
}
public boolean validateJwtToken(String jwtToken,UserDetails user){
    final String username = extarctUsername(jwtToken);
    return (username.equals(user.getUsername()));
}
public String generateJwtToken (Map<String,Object> claims, UserDetails user){
    return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
}
public <T> T extractClaims(String jwtToken, Function<Claims, T> claimsExtractor){
    final Claims claims= extractAllClaims(jwtToken);
    return claimsExtractor.apply(claims);
}
private Claims extractAllClaims(String jwtToken){
    return Jwts.parser().setSigningKey(getSignedKey()).build().parseClaimsJws(jwtToken).getBody();
}
public Key getSignedKey(){
    byte[] keyBytes = Decoders.BASE64.decode(JwtService.REMOVED);
    return Keys.hmacShaKeyFor(keyBytes);
}

}
