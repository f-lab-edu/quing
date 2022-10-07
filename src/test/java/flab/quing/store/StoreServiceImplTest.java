package flab.quing.store;

import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    StoreRepository storeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    MenuRepository menuRepository;

    @DisplayName("스토어 저장 성공")
    @Test
    void addStore() {
        //given
        User user = User.builder()
                .name("김땡땡")
                .phone("010-9999-9999")
                .build();
        user.setId(1L);

        Store store = Store.builder()
                .user(user)
                .name("가게1")
                .phoneNumber("02-1234-1234")
                .openStatus("영업중")
                .openHours("매일 11:00~23:00")
                .address("서울특별시 성동구 왕십리로 1")
                .pageLink("https://github.com/f-lab-edu/quing")
                .build();
        store.setId(1L);

        StoreRequest storeRequest = StoreRequest.builder()
                .userId(user.getId())
                .name("가게1")
                .phoneNumber("02-1234-1234")
                .openStatus("영업중")
                .openHours("매일 11:00~23:00")
                .address("서울특별시 성동구 왕십리로 1")
                .pageLink("https://github.com/f-lab-edu/quing")
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(storeRepository.save(any(Store.class))).thenReturn(store);
        System.out.println("userRepository = " + userRepository.findById(1L).get());

        //when
        StoreResponse result = storeService.addStore(storeRequest);

        //then
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("가게1");
    }


}