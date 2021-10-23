package org.example.lcs.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.exceptions.DataBaseOperationException;
import org.example.lcs.common.exceptions.InSufficienttBalanceException;
import org.example.lcs.common.responses.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = DataBaseOperationException.class)
    protected ResponseEntity<BaseResponse> exception(DataBaseOperationException exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<BaseResponse>(failedResponse(exception), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<BaseResponse> handleServerException(Exception exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<BaseResponse>(failedResponse(exception), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponse> handleMethodArgumentNoValidException(MethodArgumentNotValidException exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<BaseResponse>(failedResponseInvalidArgument(exception), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<BaseResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<BaseResponse>(failedResponse(exception), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InSufficienttBalanceException.class)
    protected ResponseEntity<BaseResponse> handleInSufficientBalance(InSufficienttBalanceException exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<BaseResponse>(failedResponse(exception), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private BaseResponse failedResponse(Exception exception) {
        return new BaseResponse(ResponseStatus.FAILED , exception.getMessage());
    }

    private BaseResponse failedResponseInvalidArgument(MethodArgumentNotValidException exception) {
        return new BaseResponse(ResponseStatus.FAILED , exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
}
