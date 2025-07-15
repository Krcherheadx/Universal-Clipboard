package dev.hisham.universal_clipboard.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor //Create a constructor for final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,@NotNull FilterChain filterChain) throws ServletException, IOException {
//Step 1: extract the token from the header
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken ;
        final String username;
        if( authHeader == null || !authHeader.startsWith("Bearer ")){//if the user doesn't have an auth header then
            // pass the request with the filteration chain
            filterChain.doFilter(request,response);// ??
            return; //Stop execution
        }
        jwtToken = authHeader.substring(7);// 7 is the number of Bearer chars, substring will extract a new string from the starting index (7) until the end
//Step 2: extract the userId from the token
        username = jwtService.extarctUsername(jwtToken);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); //if the user has a token
            // but he isn't registered within the context. userDetailsService is implemented and created in
            // ApplicationConfig, it basically checks if the username matches the one stored in the db, is not so it
            // will throw an error

            //validate if the token valid
            if(jwtService.validateJwtToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request,response);

    }
}
