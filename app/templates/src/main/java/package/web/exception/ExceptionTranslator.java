package <%=packageName %>.web.exception;

import static com.genesis.common.util.ExceptionUtil.buildErrorResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.genesis.common.dto.FieldError;

@ControllerAdvice
public class ExceptionTranslator {

	private static final String VALIDATION_FAILED = "Validation Failed!";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<FieldError> handleFieldErrors(MethodArgumentNotValidException exception,
			WebRequest request) {
		return new ResponseEntity<>(buildFieldErrorResponse(exception, request), HttpStatus.BAD_REQUEST);
	}

	public static FieldError buildFieldErrorResponse(MethodArgumentNotValidException excepion, WebRequest webRequest) {
		FieldError fieldError = new FieldError();
		List<String> errors = new ArrayList<>();
		for (org.springframework.validation.FieldError fldError : excepion.getBindingResult().getFieldErrors()) {
			errors.add(fldError.getField() + " : " + fldError.getDefaultMessage());
		}
		for (org.springframework.validation.ObjectError fldError : excepion.getBindingResult().getGlobalErrors()) {
			errors.add(fldError.getDefaultMessage());
		}
		fieldError.setErrors(errors);
		fieldError.setTimestamp(new Date());
		fieldError.setMessage(VALIDATION_FAILED);
		return fieldError;
	}
}
