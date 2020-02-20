package com.hust.software.wishbottle.exception;

import com.hust.software.wishbottle.pojo.Excep;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个全局的异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Excep defaultExceptionHandler(HttpServletRequest request,Exception e) throws Exception{
        Excep excep = new Excep();
        excep.setMessage(e.getMessage());
        excep.setUrl(request.getRequestURL());

        return excep;
    }
}
