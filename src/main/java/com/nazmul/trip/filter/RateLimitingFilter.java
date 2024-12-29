package com.nazmul.trip.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private static final int MAX_REQUESTS_PER_MINUTE = 10;
    private final Map<String, RateLimiter> ipRateLimiter = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        RateLimiter rateLimiter = ipRateLimiter.computeIfAbsent(clientIp, ip -> new RateLimiter(MAX_REQUESTS_PER_MINUTE));

        if (!rateLimiter.allowRequest()) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"error\": \"Too many requests. Please try again later.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static class RateLimiter {
        private final int maxRequests;
        private final Queue<Long> requestTimestamps = new ConcurrentLinkedQueue<>();

        public RateLimiter(int maxRequests) {
            this.maxRequests = maxRequests;
        }

        public synchronized boolean allowRequest() {
            long currentTime = System.currentTimeMillis();
            long oneMinuteAgo = currentTime - 60 * 1000;

            // Remove requests older than one minute
            while (!requestTimestamps.isEmpty() && requestTimestamps.peek() < oneMinuteAgo) {
                requestTimestamps.poll();
            }

            if (requestTimestamps.size() < maxRequests) {
                requestTimestamps.add(currentTime);
                return true;
            }

            return false;
        }
    }
}