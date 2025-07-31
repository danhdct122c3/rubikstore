package RubikStorecom.example.demo.exception;

import RubikStorecom.example.demo.dto.response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class) //bắt tất cả exception khi chạy trong spring
//    ResponseEntity<APIResponse> handlingRuntimeException(Exception e){
//        APIResponse apiResponse= new APIResponse();
//        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
@ExceptionHandler(value = Exception.class) //bắt tất cả exception khi chạy trong spring
ResponseEntity<APIResponse> handlingRuntimeException(Exception e){
    return ResponseEntity.badRequest().body(
            APIResponse.builder()
                    .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                    .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                    .build()
    );
}

    @ExceptionHandler(value = AppException.class) //bắt những lỗi logic khi chạy trong spring
    ResponseEntity<APIResponse> handlingAppException(AppException e){
       ErrorCode errorCode= e.getErrorCode();

        return ResponseEntity.badRequest().body(
                APIResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse> handlingValidation(MethodArgumentNotValidException e)
    {
        String enumKey= e.getFieldError().getDefaultMessage();

        ErrorCode errorCode= ErrorCode.INVALID_KEY; // phòng trường hợp truyền sai enum key
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException exception) {

        }

            return ResponseEntity.badRequest().body(
                    APIResponse.builder()
                            .code(errorCode.getCode())
                            .message(errorCode.getMessage())
                            .build()
            );


    }
}
