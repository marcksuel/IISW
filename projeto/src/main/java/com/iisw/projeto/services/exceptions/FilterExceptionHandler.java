package com.iisw.projeto.services.exceptions;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FilterExceptionHandler implements HandlerExceptionResolver {

    

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (ex instanceof TokenException) {
            String error = "Unauthorized access";
            HttpStatus status = HttpStatus.UNAUTHORIZED;
            StandardError err = new StandardError(new Date(), status.value(), error, ex.getMessage(), request.getRequestURI());
            try {
                response.setStatus(status.value());
                response.setContentType("application/json");
                response.getWriter().write(new ObjectMapper().writeValueAsString(err));
            } catch (IOException e) {
            	ResourceExceptionHandler exceptionHandler = new ResourceExceptionHandler();
    			exceptionHandler.exception(e, request);
    			}
            return new ModelAndView();
        }
        return null;
	}
}