package flab.quing.user;

import flab.quing.user.dto.StoreManagerRequest;
import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UpdateUserNameRequest;
import flab.quing.user.dto.UserRequest;
import flab.quing.user.dto.UserResponse;

public interface UserService {

    UserResponse signUp(UserRequest userRequest);

    UserResponse updateUserName(UpdateUserNameRequest updateUserNameRequest);

    UserResponse signIn(String phoneNumber);

    StoreManagerResponse storeSignUp(StoreManagerRequest storeManagerRequest);

    StoreManagerResponse storeSignIn(String loginId, String password);

}
