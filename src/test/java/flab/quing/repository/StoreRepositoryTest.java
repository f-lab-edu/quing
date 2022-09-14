package flab.quing.repository;

import flab.quing.store.Store;
import flab.quing.store.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Test
    void 가게추가_성공(){
        //given
        Store store = new Store();
        store.setName("추억닭발");
        store.setAddress("인천시 부평구");
        store.setOpeningHours("16:00~00:00");
        store.setPhone("010-1111-2222");
        store.setPageLink("http://test.co.kr");

        //when
        storeRepository.save(store);

        //then
        assertThat(storeRepository.findAll().size()).isEqualTo(1);
        assertThat(storeRepository.findByName(store.getName()).get().getName()).isEqualTo("추억닭발");

    }

}