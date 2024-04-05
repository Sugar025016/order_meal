// package com.order_meal.order_meal.config;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// @Component
// public class CsrfIpFilter extends OncePerRequestFilter {

//     private static final List<String> ALLOWED_IPS = Arrays.asList("127.0.0.1", "97.74.89.45");

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String ipAddress = request.getRemoteAddr();
//         if (ALLOWED_IPS.contains(ipAddress)) {
//             filterChain.doFilter(request, response);
//         } else {
//             response.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF protection enabled");
//         }
//     }
// }