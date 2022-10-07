package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;
import flab.quing.store.exception.NoSuchMenuException;
import flab.quing.store.exception.NoSuchStoreException;
import flab.quing.store.exception.NoSuchUserException;
import flab.quing.user.User;
import flab.quing.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Override
    public List<StoreResponse> getFindByStoreNameList(String name) {
        List<Store> storeList = storeRepository.findAllByName(name);

        return storeList.stream().map(Store::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StoreResponse getStore(long storeId) {
        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(NoSuchStoreException::new);

        return store.toResponse();
    }

    @Transactional
    @Override
    public StoreResponse addStore(StoreRequest storeRequest) {
        User user = userRepository.findById(storeRequest.getUserId())
                .orElseThrow(NoSuchUserException::new);
        Store addStore = Store.builder()
                .user(user)
                .address(storeRequest.getAddress())
                .pageLink(storeRequest.getPageLink())
                .openHours(storeRequest.getOpenHours())
                .openStatus(storeRequest.getOpenStatus())
                .name(storeRequest.getName())
                .phoneNumber(storeRequest.getPhoneNumber())
                .build();

        Store store = storeRepository.save(addStore);

        return store.toResponse();
    }

    @Transactional
    @Override
    public StoreResponse updateStore(StoreRequest storeRequest) {
        return null;
    }

    @Override
    public StoreResponse hideStore(long storeId) {
        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(NoSuchStoreException::new);
        store.hide();

        return store.toResponse();
    }

    @Override
    public List<MenuResponse> getMenuList(long storeId) {
        Store store = storeRepository.findByStoreId(storeId)
                .orElseThrow(NoSuchStoreException::new);

        List<Menu> menu = menuRepository.findByStore(store);

        return menu.stream().map(Menu::toResponse).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MenuResponse addMenu(MenuRequest menuRequest) {
        Store store = storeRepository.findByStoreId(menuRequest.getStoreId())
                .orElseThrow(NoSuchStoreException::new);
        Menu menu = Menu.builder()
                .store(store)
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .imageUrl(menuRequest.getImageUrl())
                .build();

        Menu createdMenu = menuRepository.save(menu);

        return createdMenu.toResponse();
    }

    @Override
    public MenuResponse hideMenu(long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(NoSuchMenuException::new);
        menu.hide();

        return menu.toResponse();
    }

    @Override
    public MenuResponse updateMenu(MenuRequest menuRequest) {
        return null;
    }
}
