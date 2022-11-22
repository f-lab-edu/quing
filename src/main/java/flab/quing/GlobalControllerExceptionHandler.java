package flab.quing;

import flab.quing.review.NoSuchReviewException;
import flab.quing.store.exception.NoSuchMenuException;
import flab.quing.store.exception.NoSuchStoreException;
import flab.quing.store.exception.NoSuchUserException;
import flab.quing.user.exception.SignUpException;
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
    //Review
    @ExceptionHandler(NoSuchReviewException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "review")
    @ResponseBody
    public void handleReviewNotFound() {
        log.error("404");
    }

    //Store
    @ExceptionHandler(NoSuchMenuException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "store.menu")
    @ResponseBody
    public void handleMenuNotFound() {
        log.error("404");
    }

    @ExceptionHandler(NoSuchStoreException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "store")
    @ResponseBody
    public void handleStoreNotFound() {
        log.error("404");
    }

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "store.user")
    @ResponseBody
    public void handleUserNotFound() {
        log.error("404");
    }

    //User
    @ExceptionHandler(SignUpException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "user")
    @ResponseBody
    public void handleUserSignUpBadRequest() {
        log.error("400");
    }

    //Waiting
    @ExceptionHandler(DuplicateWaitingException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Waiting Duplicate")
    @ResponseBody
    public void handleWaitingConflict() {
        log.error("409");
    }

    @ExceptionHandler(NoSuchWaitingException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "waiting")
    @ResponseBody
    public void handleWaitingNotfound() {
        log.error("404");
    }

    //Others
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request")
    @ResponseBody
    public void handleIllegalArgument() {
        log.error("400");
    }
}