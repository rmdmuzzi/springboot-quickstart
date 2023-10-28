package com.raymond.quickstart.config;

import com.raymond.quickstart.domain.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * SpringMVC exception handler
 *
 * @author raymondmuzzi
 * @since 2023-10-28 19:03:33
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {

    /**
     * intercept all exception info
     *
     * @param ex exception during execution
     */
    @ExceptionHandler
    public R doException(Exception ex) {
        R r = new R(false, "Server error!");
        r.setResMsg(ex.getMessage());
        ex.printStackTrace();
        return r;
    }
}
