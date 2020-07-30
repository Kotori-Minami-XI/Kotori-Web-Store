package com.Kotori.store.product.excepetion;

import com.Kotori.common.exception.BizCode;
import com.Kotori.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *  Dealing with all exceptions happened in controllers
 */
@RestControllerAdvice(basePackages = "com.Kotori.store.product.controller")
public class ProductControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map<String, String> map = new HashMap();
        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors) {
            String message = error.getDefaultMessage();
            String field = error.getField();
            map.put(field, message);
        }
        return R.error(BizCode.VALID_EXCEPTION.getCode(), BizCode.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        e.printStackTrace();
        return R.error(BizCode.UNKNOWN_EXCEPTION.getCode(), BizCode.UNKNOWN_EXCEPTION.getMsg());
    }

}
