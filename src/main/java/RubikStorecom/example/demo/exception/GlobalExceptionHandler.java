package RubikStorecom.example.demo.exception;

import RubikStorecom.example.demo.dto.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class) //bắt những lỗi logic khi chạy trong spring
    ResponseEntity<String> handlingRuntimeException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException e)
    {
        return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage()); // lấy ra message đã khai báo trong validation ở request
    }
}
