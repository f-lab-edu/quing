package flab.quing.waiting;

import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UserResponse;
import flab.quing.waiting.dto.WaitingAppendRequest;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/quing")
public class QuingController {

    private final QuingService quingService;


    @GetMapping
    List<WaitingResponse> list(
            @Parameter(hidden = true)
            @SessionAttribute(name = "AUTH_STORE")
            StoreManagerResponse storeManagerResponse
    ) {
        long storeId = storeManagerResponse.getStoreId();

        return quingService.getList(storeId);
    }

    @GetMapping("count-forward")
    long countForward(
            @Parameter(hidden = true)
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse
    ) {
        return quingService.countForward(userResponse.getUserId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    WaitingResponse append(
            @Parameter(hidden = true)
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse,
            @RequestBody WaitingAppendRequest waitingAppendRequest
    ) {
        WaitingRequest waitingRequest = WaitingRequest.builder()
                .userId(userResponse.getUserId())
                .storeId(waitingAppendRequest.getStoreId())
                .build();

        WaitingResponse waitingResponse = quingService.append(waitingRequest);

        quingService.sendRegisterMessage(waitingResponse.getId());

        return waitingResponse;
    }

    @PatchMapping
    WaitingResponse doneWaiting(
            @Parameter(hidden = true)
            @SessionAttribute(name = "AUTH_STORE")
            StoreManagerResponse storeManagerResponse,
            long userId
    ) {
        WaitingResponse waitingResponseByUserId = quingService.getByUserId(userId);

        return quingService.doneWaiting(waitingResponseByUserId.getId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void cancelWaiting(
            @Parameter(hidden = true)
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse
    ) {
        WaitingResponse waitingResponseByUserId = quingService.getByUserId(userResponse.getUserId());

        quingService.cancelWaiting(waitingResponseByUserId.getId());
    }
}
