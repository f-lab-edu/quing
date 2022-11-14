package flab.quing;

import flab.quing.waiting.exception.DuplicateWaitingException;
import flab.quing.waiting.exception.NoSuchWaitingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(DuplicateWaitingException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Waiting Duplicate, try again")  // 409
    @ResponseBody
    public void handleWaitingConflict() {
        log.error("409");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request")  // 400
    @ResponseBody
    public void handleIllegalArgument() {
        System.out.println("400");

    }

    @ExceptionHandler(NoSuchWaitingException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "content not found")  // 404
    @ResponseBody
    public void handleNotFound() {
        System.out.println("404");
    }

}