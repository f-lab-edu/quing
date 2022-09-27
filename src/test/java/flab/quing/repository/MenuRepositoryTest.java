package flab.quing.repository;

import flab.quing.store.Menu;
import flab.quing.store.Store;
import flab.quing.store.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MenuRepositoryTest {
    @Autowired
    MenuRepository menuRepository;

    @Test
    void 메뉴추가_성공() {
        Store store = new Store();
        store.setName("큐잉상점");

        Menu menu1 = new Menu();
        menu1.setStore(store);
        menu1.setName("진라면매운맛");
        menu1.setPrice(5000);

        menuRepository.save(menu1);

        assertThat(menuRepository.findByStore(store).get().getStore().getName())
                .isEqualTo(store.getName());

    }

}