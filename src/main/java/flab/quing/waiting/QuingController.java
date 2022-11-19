package flab.quing.waiting;

import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UserResponse;
import flab.quing.waiting.dto.WaitingRequest;
import flab.quing.waiting.dto.WaitingResponse;
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
    private List<WaitingResponse> list(
            @SessionAttribute(name = "AUTH_STORE")
            StoreManagerResponse storeManagerResponse
    ) {
        long storeId = storeManagerResponse.getStoreId();

        return quingService.getList(storeId);
    }

    @GetMapping("count-forward")
    private long countForward(@SessionAttribute(name = "AUTH_USER")
                             UserResponse userResponse
    ) {
        return quingService.countForward(userResponse.getUserId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private WaitingResponse append(
            @SessionAttribute(name = "AUTH_USER")
            UserResponse userResponse,
            @RequestBody long storeId
    ) {
        WaitingRequest waitingRequest = WaitingRequest.builder()
                .userId(userResponse.getUserId())
                .storeId(storeId)
                .build();

        WaitingResponse waitingResponse = quingService.append(waitingRequest);

        quingService.sendRegisterMessage(waitingResponse.getId());

        return waitingResponse;
    }
}
