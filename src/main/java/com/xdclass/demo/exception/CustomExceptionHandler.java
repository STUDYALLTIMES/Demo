package com.xdclass.demo.exception;


import com.xdclass.demo.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {


    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonData handle(Exception e){

        logger.error("[ 系统异常 ]{}",e);

        if( e instanceof XDException ){

            XDException xdException = (XDException) e;

            return JsonData.buildError(xdException.getCode(),xdException.getMsg());

        }else {

            return JsonData.buildError("全局异常，未知错误");

        }


    }

}
