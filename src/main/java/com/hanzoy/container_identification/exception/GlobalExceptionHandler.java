package com.hanzoy.container_identification.exception;


import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.exception.exception.SignErrorException;
import com.hanzoy.container_identification.exception.exception.TokenErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        return CommonResult.validateFailed("请求参数解析异常");
    }

    @ExceptionHandler(TokenErrorException.class)
    public CommonResult handleTokenErrorException(TokenErrorException e){
        return CommonResult.tokenError(e.getMessage());
    }

    @ExceptionHandler(SignErrorException.class)
    public CommonResult handleTokenErrorException(SignErrorException e){
        return CommonResult.fail(e.getMessage());
    }
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    public CommonResult handleIndexOutOfBoundsException(IndexOutOfBoundsException e){
//        return CommonResult.validateFailed("数据内容异常");
//    }
}
