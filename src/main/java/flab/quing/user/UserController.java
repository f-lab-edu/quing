package flab.quing.user;

import flab.quing.user.dto.StoreManagerLoginRequest;
import flab.quing.user.dto.StoreManagerRequest;
import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UserRequest;
import flab.quing.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUpUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.signUp(userRequest);

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponse> signInUser(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        UserResponse userResponse = userService.signIn(userRequest.getName(), userRequest.getPhoneNumber());

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("AUTH_USER", userResponse);

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/store-sign-up")
    public ResponseEntity<StoreManagerResponse> signUpStore(@RequestBody StoreManagerRequest storeManagerRequest) {
        StoreManagerResponse storeManagerResponse = userService.storeSignUp(storeManagerRequest);

        return ResponseEntity.ok(storeManagerResponse);
    }

    @PostMapping("/store-sign-in")
    public ResponseEntity<StoreManagerResponse> signInStore(@RequestBody StoreManagerLoginRequest storeManagerLoginRequest, HttpServletRequest request) {
        StoreManagerResponse storeManagerResponse
                = userService.storeSignIn(storeManagerLoginRequest.getLoginId(), storeManagerLoginRequest.getPassword());

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("AUTH_STORE", storeManagerResponse);

        return ResponseEntity.ok(storeManagerResponse);
    }
}
