package com.jmalltech.service;

import com.auth0.jwt.interfaces.Claim;
import com.jmalltech.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtTokenService {
    public Long getClientIdIfClient(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new SecurityException("Unauthorized - Token is missing or malformed");
        }
        String token = authHeader.substring(7);
        return getClientIdFromTokenIfClient(token);
    }

    private Long getClientIdFromTokenIfClient(String token) {
        Map<String, Claim> claims = JwtUtil.validateToken(token);
        if (claims == null || !claims.get("role").asString().equals("CLIENT")) {
            throw new SecurityException("Access Denied - Token is invalid or not for a client");
        }
        return claims.get("id").asLong();
    }
}
