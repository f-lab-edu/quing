package flab.quing.user;

import flab.quing.user.dto.StoreManagerRequest;
import flab.quing.user.dto.StoreManagerResponse;
import flab.quing.user.dto.UserRequest;
import flab.quing.user.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    StoreManagerRepository storeManagerRepository;

    @Test
    @DisplayName("사용자 추가 성공")
    void signUp_User_Success() {
        //given
        User user = User.builder()
                .phoneNumber("010-1234-5678")
                .name("홍길동")
                .build();
        user.setId(2L);

        UserRequest userRequest = UserRequest.builder()
                .phoneNumber("010-1234-5678")
                .name("홍길동")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        UserResponse result = userService.signUp(userRequest);

        //then
        assertThat(result.getUserId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("StoreManager 회원가입 성공")
    void signUp_StoreManager_Success() {
        //given
        StoreManager storeManager = StoreManager.builder()
                .loginId("yuseon")
                .encryptedPassword("12345")
                .name("홍길동")
                .phoneNumber("010-1234-5678")
                .build();
        storeManager.setId(1L);

        StoreManagerRequest storeManagerRequest = StoreManagerRequest.builder()
                .loginId("yuseon")
                .password("12345")
                .name("홍길동")
                .phoneNumber("010-1234-5678")
                .build();
        when(storeManagerRepository.save(any(StoreManager.class))).thenReturn(storeManager);

        //when
        StoreManagerResponse result = userService.storeSignUp(storeManagerRequest);

        //then
        assertThat(result.getLoginId()).isEqualTo("yuseon");
    }
}