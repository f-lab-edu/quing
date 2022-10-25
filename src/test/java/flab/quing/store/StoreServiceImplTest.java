package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
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

    @Test
    @DisplayName("스토어 저장 성공")
    void add_Store_Success() {
        //given
        User user = User.builder()
                .name("김땡땡")
                .phoneNumber("010-9999-9999")
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

    @Test
    @DisplayName("메뉴 저장 성공")
    void add_Menu_Success() {
        //given
        User user = User.builder()
                .name("김땡땡")
                .phoneNumber("010-9999-9999")
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

        Menu menu1 = Menu.builder()
                .store(store)
                .name("시카고피자")
                .price(22000)
                .imageUrl("/image/pizza.png")
                .build();
        menu1.setId(1L);

        MenuRequest menuRequest1 = MenuRequest.builder()
                .storeId(store.getId())
                .name("시카고피자")
                .price(22000)
                .imageUrl("/image/pizza.png")
                .build();

        Menu menu2 = Menu.builder()
                .store(store)
                .name("페퍼로니피자")
                .price(20000)
                .imageUrl("/image/pepperoniPizza.png")
                .build();
        menu2.setId(2L);

        MenuRequest menuRequest2 = MenuRequest.builder()
                .storeId(store.getId())
                .name("페퍼로니피자")
                .price(20000)
                .imageUrl("/image/pepperoniPizza.png")
                .build();

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(menuRepository.save(menu1)).thenReturn(menu1);
        when(menuRepository.save(menu2)).thenReturn(menu2);

        //when
        MenuResponse result1 = storeService.addMenu(menuRequest1);
        MenuResponse result2 = storeService.addMenu(menuRequest2);

        //then
        assertThat(result1.getMenuId()).isEqualTo(1L);
        assertThat(result1.getName()).isEqualTo("시카고피자");
        assertThat(result2.getName()).isEqualTo("페퍼로니피자");
    }

    @Test
    @DisplayName("스토어 수정 성공")
    public void update_Store_Success() {
        //given
        User user = User.builder()
                .name("김땡땡")
                .phoneNumber("010-9999-9999")
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
                .storeId(store.getId())
                .name("가게3")
                .phoneNumber("02-1234-1234")
                .openStatus("영업중")
                .openHours("매일 11:00~21:00")
                .address("서울특별시 성동구 왕십리로 1")
                .pageLink("https://github.com/f-lab-edu/quing")
                .build();

        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        //when
        StoreResponse result = storeService.updateStore(storeRequest);

        //then
        assertThat(result.getName()).isEqualTo("가게3");
    }

    @Test
    @DisplayName("메뉴 수정 성공")
    public void menu_update_success() {
        //given
        User user = User.builder()
                .name("김땡땡")
                .phoneNumber("010-9999-9999")
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

        Menu menu1 = Menu.builder()
                .store(store)
                .name("시카고피자")
                .price(22000)
                .imageUrl("/image/pizza.png")
                .build();
        menu1.setId(1L);

        MenuRequest menuRequest1 = MenuRequest.builder()
                .storeId(store.getId())
                .menuId(menu1.getId())
                .name("알리오올리오")
                .price(18000)
                .imageUrl("/image/pizza.png")
                .build();

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu1));

        //when
        MenuResponse result = storeService.updateMenu(menuRequest1);

        //then
        assertThat(result.getName()).isEqualTo("알리오올리오");
    }
}