// package com.order_meal.order_meal.config;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.security.web.csrf.CsrfToken;
// import org.springframework.security.web.csrf.CsrfTokenRepository;
// import org.springframework.stereotype.Service;

// @Service
// public class CsrfTokenService {

//     private final CsrfTokenRepository csrfTokenRepository;

//     public CsrfTokenService(CsrfTokenRepository csrfTokenRepository) {
//         this.csrfTokenRepository = csrfTokenRepository;
//     }

//     public void printCsrfToken(HttpServletRequest request) {
//         CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
//         if (csrfToken != null) {
//             System.out.println("CsrfToken: " + csrfToken.getToken());
//         } else {
//             System.out.println("CsrfToken not found!");
//         }
//     }
// }
