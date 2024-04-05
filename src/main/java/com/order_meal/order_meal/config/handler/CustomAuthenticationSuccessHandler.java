package com.order_meal.order_meal.config.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	// @Autowired
	// private DataSource dataSource;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // 在這裡處理登錄成功後的邏輯
        // 例如：組裝 JSON 數據
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("ok", true);
        responseData.put("message", "登錄成功");
        responseData.put("status", 200);

        // responseData.put("token", dataSource);
        // 將 JSON 寫入 response 中
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
    }
}
