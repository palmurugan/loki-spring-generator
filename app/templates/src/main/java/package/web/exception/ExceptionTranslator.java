<%#
 Copyright 2019-2020 the original author or authors from the Loki project.

 This file is part of the Loki project.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-%>
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

import com.genesis.common.dto.ErrorResponse;
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
		fieldError.setErrors(errors);
		fieldError.setTimestamp(new Date());
		fieldError.setMessage(VALIDATION_FAILED);
		return fieldError;
	}
}
