package br.com.hdi.apijava.controller.exception;

import br.com.hdi.apijava.controller.response.ApiResponse;
import br.com.hdi.apijava.exception.BadRequestException;
import br.com.hdi.apijava.exception.ExternalRepositoryException;
import br.com.hdi.apijava.exception.ResourceNotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Log4j2
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class, SecurityException.class })
	protected ResponseEntity<ApiResponse<?>> handleConflict(RuntimeException exception) {
		HttpStatus httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = exception.getMessage();

		try {
			if (exception instanceof ConstraintViolationException) {
				for (ConstraintViolation<?> cv : ((ConstraintViolationException) exception).getConstraintViolations()) {
					String path = cv.getPropertyPath().toString();
					String property = path.substring(path.lastIndexOf('.') + 1, path.length());

					message = String.format("Erro no formato do request informado: ['%s' -> '%s']. %s.", property,
							cv.getInvalidValue(), cv.getMessage());
				}

			}
		} catch (Exception ex) {
			log.warn("Erro ao recuperar mensagem de uma ConstraintViolationExcepton.", ex);
		}

		ApiResponse<?> response = ApiResponse.builder().success(false).message(message).build();

		return new ResponseEntity<>(response, httpStatusCode);
	}

	@ExceptionHandler(ExternalRepositoryException.class)
	ResponseEntity<?> handleExternalRepositoryException(HttpServletRequest req, Exception ex) {
		ExternalRepositoryException e = (ExternalRepositoryException) ex;

		ApiResponse<?> response = ApiResponse.builder().success(false).message(e.getMessage()).build();

		return ResponseEntity.status(e.getStatusCode()).body(response);
	}

	@ExceptionHandler(BadRequestException.class)
	ResponseEntity<?> handleBadRequest(HttpServletRequest req, Exception ex) {
		BadRequestException e = (BadRequestException) ex;

		ApiResponse<?> response = ApiResponse.builder().success(false).message(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}	

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
		ApiResponse<?> response = ApiResponse.builder().success(false).message(ex.getMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		ApiResponse<?> response = ApiResponse.builder().success(false).message(ex.getMessage()).build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String errorDetails = "Erro no formato do request informado: " + exception.getMessage();

		if (exception instanceof MethodArgumentTypeMismatchException) {
			errorDetails = String.format(
					"Erro no formato do request informado: ['%s' -> '%s']. Tipo de valor imcompatível para o parâmetro informado.",
					((MethodArgumentTypeMismatchException) exception).getName(), exception.getValue());
		}

		ApiResponse<?> response = ApiResponse.builder().success(false).message(errorDetails).build();

		return handleExceptionInternal(exception, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	public ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		String errorDetails = String.format(
				"Erro no formato do request informado: ['%s' -> 'null']. Este campo é obrigatório.",
				exception.getParameterName());

		ApiResponse<?> response = ApiResponse.builder().success(false).message(errorDetails).build();

		return handleExceptionInternal(exception, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String genericMessage = "Erro no formato do request informado: " + exception.getMessage();
		String errorDetails = genericMessage;

		if (exception.getCause() instanceof InvalidFormatException) {
			InvalidFormatException ifx = (InvalidFormatException) exception.getCause();
			if (ifx.getTargetType().isEnum()) {
				String enumValues = getEnumValues(ifx.getTargetType());

				errorDetails = String.format(
						"Erro no formato do request informado: ['%s' -> '%s']. O valor deve ser um dos seguintes: %s.",
						ifx.getPath().get(0).getFieldName(), ifx.getValue(), enumValues);
			}
		}

		ApiResponse<?> response = ApiResponse.builder().success(false).message(errorDetails).build();

		return handleExceptionInternal(exception, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiResponse<?> response = ApiResponse.builder().success(false)
				.message(getFieldErrorsMessage(ex.getFieldErrors(), ex.getBindingResult())).build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ApiResponse<?> response = ApiResponse.builder().success(false)
				.message(getFieldErrorsMessage(ex.getFieldErrors(), ex.getBindingResult())).build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	private String getFieldErrorsMessage(List<FieldError> fieldErrors, BindingResult bindingResults) {
		StringBuilder sbErrorMessage = new StringBuilder("Erro no formato do request informado: ");

		for (int increment = 0; increment < fieldErrors.size(); increment++) {
			FieldError error = fieldErrors.get(increment);

			String jsonPropertyFieldName = getJsonPropertyFieldName(bindingResults.getTarget(), error.getField());

			sbErrorMessage.append("['").append(jsonPropertyFieldName).append("' -> '").append(error.getRejectedValue())
					.append("': ").append(error.getDefaultMessage()).append("]");

			if (increment + 1 < fieldErrors.size())
				sbErrorMessage.append(", ");
		}

		return sbErrorMessage.toString();
	}

	private String getJsonPropertyFieldName(Object targetObject, String fieldName) {
		String jsonPropertyFieldName = fieldName;
		Class<?> clazz = null;

		try {
			clazz = (Class<?>) targetObject.getClass();

			Field field = clazz.getDeclaredField(fieldName);

			if (field.isAnnotationPresent(JsonProperty.class))
				return field.getAnnotation(JsonProperty.class).value();
		} catch (Exception e) {
			log.warn("Não foi possível recuperar o valor da anotação 'JsonProperty' no campo {} da classe {}",
					fieldName, clazz != null ? clazz.getCanonicalName() : "");
		}

		return jsonPropertyFieldName;

	}

	private String getEnumValues(Class<?> enumClass) {
		Object[] enumConstants = enumClass.getEnumConstants();
		Object[] enumValues = new String[enumConstants.length];

		int index = 0;
		for (Object enumConstant : enumConstants) {
			Object enumValue;

			try {
				Method m = enumClass.getMethod("getCode");
				enumValue = (String) m.invoke(enumConstant);
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
				enumValue = enumConstant.toString();
			}

			enumValues[index++] = enumValue;
		}

		return Arrays.toString(enumValues);
	}
}
