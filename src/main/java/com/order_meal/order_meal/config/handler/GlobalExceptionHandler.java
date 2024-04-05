package com.order_meal.order_meal.config.handler;

import javax.management.relation.RelationNotFoundException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import com.order_meal.order_meal.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {
 
    // 註記將返回(return)的資料放入HTTP Response Body中
    // @ResponseBody
    // // 註記這個例外處理的範圍是所有的Exception.class，所有例外都會歸在Exception類，代表全域的意思
    // @ExceptionHandler(Exception.class)
    // // 將外部的Exception內容透過參數傳進來
    // public ResponseEntity<Object> GlobalExceotion(Exception exception) {
    //     Logger myLogger = LoggerFactory.getLogger(exception.getClass().getName());
    //     myLogger.error("Global Exception Handler. Message: {}", exception.getMessage());
 
    //     // 組裝Response Body
    //     String jsonRspBody = new FormatConverter()
    //             .Object2JsonString(new RspBody("9999", "Global Exception Handler", exception.getMessage()));
 
    //     // 設定Response的Header資訊
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("Content-Type", "application/json; charset=utf-8");
 
    //     // 回覆一個Response實體
    //     return new ResponseEntity<>(jsonRspBody, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        // 校驗失敗，統一處理
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    // public class RestResponseEntityExceptionHandler implements WebMvcConfigurer{

    // @ResponseBody
    // @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    // protected ResponseEntity<Object> handleConflict(
    //         RuntimeException ex, WebRequest request) {
    //     String bodyOfResponse = "This should be application specific";
    //     // return handleExceptionInternal(ex, bodyOfResponse,
    //     // new HttpHeaders(), HttpStatus.CONFLICT, request);
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    // }

    // @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        // 自定義錯誤回應，例如返回一個包含錯誤訊息的 JSON 物件
        String errorMessage = "Not found: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<ValidationError> handleValidationException(MethodArgumentNotValidException ex) {
    //     BindingResult bindingResult = ex.getBindingResult();
    //     ValidationError validationError = new ValidationError();

    //     // 获取所有验证错误并添加到ValidationError对象中
    //     bindingResult.getFieldErrors().forEach(fieldError -> {
    //         validationError.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
    //     });

    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(validationError);
    // }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(ResponseStatusException ex) {
        // String exceptionClassName = ex.getClass().getName();
        ErrorResponse errorResponse = new ErrorResponse();

        // validationError.addFieldError(exceptionClassName,ex.getReason());

        // 获取异常消息
        String errorMessage = ex.getLocalizedMessage();

        // 从异常消息中提取类名
        String className = extractClassNameFromErrorMessage(errorMessage);

        // validationError.addFieldError(className, ex.getReason());
        errorResponse.setCode(402);
        errorResponse.setMessage(className);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RelationNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // @ExceptionHandler(OtherException.class)
    // public ResponseEntity<Object> handleOtherException(OtherException ex) {
    // // 自定義其他異常的處理邏輯，返回適當的錯誤回應
    // String errorMessage = "Other exception: " + ex.getMessage();
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    // }

    private String extractClassNameFromErrorMessage(String errorMessage) {
        // 在实际项目中，您可以编写逻辑来从错误消息中提取类名
        // 这里只是一个示例，您可能需要根据实际的错误消息格式来实现逻辑
        // 以下示例假设错误消息的格式是 "ClassNotFound: 类名"
        String[] parts = errorMessage.split(": ");
        if (parts.length >= 2) {
            return parts[1].trim();
        } else {
            return "UnknownClass";
        }
    }
}