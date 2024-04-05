// package com.order_meal.order_meal.config;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

// public class CsrfInterceptor implements HandlerInterceptor {

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//             throws Exception {
//         if (!request.getMethod().equals("GET")) {
//             // 檢查CSRF令牌是否存在並與請求中的令牌匹配
//             String csrfToken = (String) request.getSession().getAttribute("csrfToken");
//             String requestToken = request.getHeader("X-CSRF-TOKEN");
//             if (csrfToken == null || !csrfToken.equals(requestToken)) {
//                 // 如果CSRF令牌不匹配，返回403禁止訪問
//                 response.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF token mismatch");
//                 return false;
//             }
//         }
//         return true;
//     }

//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//             ModelAndView modelAndView) throws Exception {
//         // 在這裡可以添加任何後置處理邏輯
//     }

//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//             Exception ex) throws Exception {
//         // 在這裡可以添加任何完成後的邏輯
//     }
// }