package com.ecom.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
	MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
	return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> myAdminExceptionHandler(AdminException ae,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),ae.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myAdminExceptionHandler(LoginException le,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),le.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException user,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),user.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> CartExceptionHandler(CartException cart,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),cart.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> ProductExceptionHandler(ProductException p,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),p.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<MyErrorDetails> FeedbackExceptionHandler(FeedbackException f,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),f.getMessage(),req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(RefundedOrderException.class)
	public ResponseEntity<MyErrorDetails> myRefundedOrderExceptionHandler(RefundedOrderException ae,WebRequest req){
		
		MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(),ae.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
}
