//package com.agrotep.web;
//
//import com.agrotep.model.ErrorDescription;
//import com.agrotep.model.ErrorResponse;
//import com.agrotep.model.ValidationErrorResponse;
//import io.swagger.v3.oas.annotations.Hidden;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;
//import org.slf4j.Logger;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import jakarta.persistence.EntityNotFoundException;
//
//
//import java.util.List;
//
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//import static org.slf4j.LoggerFactory.getLogger;
//
//@Hidden
//@RestControllerAdvice(annotations = RestController.class)
//@FieldDefaults(level= AccessLevel.PRIVATE)
//public class ExceptionController {
//    static final Logger logger = getLogger(ExceptionController.class);
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ValidationErrorResponse badRequest(MethodArgumentNotValidException exception) {
//        final BindingResult bindingResult = exception.getBindingResult();
//        return new ValidationErrorResponse(buildMessage(bindingResult), buildErrors(bindingResult));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ErrorResponse error(EntityNotFoundException exception) {
//        return new ErrorResponse(exception.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(IllegalStateException.class)
//    public ErrorResponse conflict(IllegalStateException exception) {
//        return new ErrorResponse(exception.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    public ErrorResponse error(RuntimeException exception) {
//        logger.error("", exception);
//        return new ErrorResponse(exception.getMessage());
//    }
//
//    private String buildMessage(BindingResult bindingResult) {
//        return String.format("Error on %s, rejected errors [%s]",
//                bindingResult.getTarget(),
//                bindingResult.getAllErrors()
//                        .stream()
//                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                        .collect(joining(";")));
//    }
//
//    private List<ErrorDescription> buildErrors(BindingResult bindingResult) {
//        return bindingResult.getFieldErrors()
//                .stream()
//                .map(e -> new ErrorDescription(e.getField(), e.getDefaultMessage()))
//                .collect(toList());
//    }
//}
