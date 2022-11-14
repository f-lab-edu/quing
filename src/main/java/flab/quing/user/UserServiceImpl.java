package flab.quing.user;

import flab.quing.store.exception.NoSuchUserException;
import flab.quing.user.dto.StoreManagerRequest;
import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UserRequest;
import flab.quing.user.dto.UserResponse;
import flab.quing.user.exception.SignInException;
import flab.quing.user.exception.SignUpException;
import flab.quing.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final StoreManagerRepository storeManagerRepository;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public UserResponse signUp(UserRequest userRequest) {
        checkUserDuplication(userRequest.getPhoneNumber());
        User user = User.builder()
                .name(userRequest.getName())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
        User createdUser = userRepository.save(user);
        return createdUser.toResponse();
    }

    @Override
    public UserResponse updateUserName(UserRequest userRequest) {
        User user = userRepository.findByPhoneNumber(userRequest.getPhoneNumber())
                .orElseThrow(NoSuchUserException::new);
        user.setName(userRequest.getName());
        return user.toResponse();
    }

    @Override
    public UserResponse signIn(String phoneNumber) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()) {
            UserRequest createdUser = UserRequest.builder()
                    .phoneNumber(phoneNumber)
                    .build();
            return signUp(createdUser);
        }
        return optionalUser.get().toResponse();
    }

    @Transactional
    @Override
    public StoreManagerResponse storeSignUp(StoreManagerRequest storeManagerRequest) {
        checkStoreManagerDuplication(storeManagerRequest.getLoginId());
        StoreManager storeManager = StoreManager.builder()
                .loginId(storeManagerRequest.getLoginId())
                .encryptedPassword(passwordEncoder.hashPassword(storeManagerRequest.getPassword()))
                .name(storeManagerRequest.getName())
                .phoneNumber(storeManagerRequest.getPhoneNumber())
                .build();
        StoreManager createdStoreManager = storeManagerRepository.save(storeManager);
        return createdStoreManager.toResponse();
    }

    @Override
    public StoreManagerResponse storeSignIn(String loginId, String password) {
        StoreManager findStoreManager = storeManagerRepository.findByLoginId(loginId)
                .orElseThrow(() -> new SignInException("User not found."));

        boolean isMatched = passwordEncoder.isMatched(password, findStoreManager.getEncryptedPassword());
        if (!isMatched) {
            throw new SignInException("Password does not match.");
        }

        return findStoreManager.toResponse();
    }

    private void checkUserDuplication(String phoneNumber) {
        boolean isParent = userRepository.findByPhoneNumber(phoneNumber).isPresent();
        if (isParent) {
            throw new SignUpException("request phoneNumber(" + phoneNumber +") is already exists");
        }
    }

    private void checkStoreManagerDuplication(String loginId) {
        boolean isParent = storeManagerRepository.findByLoginId(loginId).isPresent();
        if (isParent) {
            throw new SignUpException("request id("+ loginId +")is already exists");
        }
    }
}
