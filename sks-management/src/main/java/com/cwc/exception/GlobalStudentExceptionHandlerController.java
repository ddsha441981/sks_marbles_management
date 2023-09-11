package com.cwc.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalStudentExceptionHandlerController{// extends ResponseEntityExceptionHandler{


	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResponse> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            
//            errors.put(fieldName, message);
//        });
//		
//        ApiResponse response = new ApiResponse();
//        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        response.setTimestamp(new Date());
//        response.setMessage("Bad Request");
////        response.setErrors(errors);
//        response.getDescription();
//        response.setPath(""); // Set the appropriate path if needed
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//	
	
	
	@ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<ApiResponse> handleDuplicateCategoryException(DuplicateCategoryException ex) {
        Map<String, Object> responseValues = new HashMap<>();
        responseValues.put("statusCode", HttpStatus.CONFLICT.value());
        responseValues.put("timestamp", new Date());
        responseValues.put("message", "Conflict");
        responseValues.put("description", ex.getMessage());
        responseValues.put("path", ""); // Set the appropriate path if needed

        ApiResponse response = buildApiResponse(responseValues);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> responseValues = new HashMap<>();
        responseValues.put("statusCode", HttpStatus.CONFLICT.value());
        responseValues.put("timestamp", new Date());
        responseValues.put("message", "Conflict");
        responseValues.put("description", ex.getMessage());
        responseValues.put("path", ""); // Set the appropriate path if needed

        ApiResponse response = buildApiResponse(responseValues);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ApiResponse buildApiResponse(Map<String, Object> responseValues) {
        ApiResponse response = new ApiResponse();
        response.setStatusCode((Integer) responseValues.get("statusCode"));
        response.setTimestamp((Date) responseValues.get("timestamp"));
        response.setMessage((String) responseValues.get("message"));
        response.setDescription((String) responseValues.get("description"));
        response.setPath((String) responseValues.get("path"));
        return response;
    }
	
	
	
}
