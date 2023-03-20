package com.xuecheng.base.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(XueChengCommonException.class)
    public ExceptionResponse handlerXueChengCommonException(XueChengCommonException e) {
        log.error("异常信息：{}", e.getMessage());
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleUnknownException(Exception e) {
        e.printStackTrace();
        log.error("未知异常：{}", e.getMessage());
        return new ExceptionResponse(ExceptionEnum.UNKNOWN_ERR.getErrMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationException(MethodArgumentNotValidException e) {
        String errMessage = "";
        for (FieldError fieldError : e.getFieldErrors()) {
            errMessage += fieldError.getDefaultMessage() + " ";
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(errMessage);
        return exceptionResponse;
    }
}
