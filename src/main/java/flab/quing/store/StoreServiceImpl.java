package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;
import flab.quing.store.exception.NoSuchMenuException;
import flab.quing.store.exception.NoSuchStoreException;
import flab.quing.user.StoreManager;
import flab.quing.user.StoreManagerRepository;
import flab.quing.user.exception.NoSuchStoreManagerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;
    private final StoreManagerRepository storeManagerRepository;

    @Override
    public List<StoreResponse> getStoreList() {
        return storeRepository.findAll()
                .stream()
                .map(Store::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StoreResponse getStore(long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(NoSuchStoreException::new);

        return store.toResponse();
    }

    @Transactional
    @Override
    public StoreResponse addStore(StoreRequest storeRequest) {
        StoreManager storeManager = storeManagerRepository.findById(storeRequest.getStoreManagerId())
                .orElseThrow(NoSuchStoreManagerException::new);
        Store addStore = Store.builder()
                .address(storeRequest.getAddress())
                .pageLink(storeRequest.getPageLink())
                .openHours(storeRequest.getOpenHours())
                .openStatus(storeRequest.getOpenStatus())
                .name(storeRequest.getName())
                .phoneNumber(storeRequest.getPhoneNumber())
                .build();

        Store store = storeRepository.save(addStore);
        storeManager.setStore(store);

        return store.toResponse();
    }

    @Transactional
    @Override
    public StoreResponse updateStore(long storeId, StoreRequest storeRequest) {
        Store updateStore = storeRepository.findById(storeId)
                .orElseThrow(NoSuchStoreException::new);
        updateStore.update(storeRequest);
        storeRepository.save(updateStore);

        return updateStore.toResponse();
    }

    @Override
    public StoreResponse hideStore(long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(NoSuchStoreException::new);
        store.hide();

        return store.toResponse();
    }

    @Override
    public List<MenuResponse> getMenuList(long storeId) {
        return menuRepository.findByStoreId(storeId)
                .stream()
                .map(Menu::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public MenuResponse addMenu(MenuRequest menuRequest) {
        Store store = storeRepository.findById(menuRequest.getStoreId())
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
        Menu updateMenu = menuRepository.findById(menuRequest.getMenuId())
                .orElseThrow(NoSuchMenuException::new);
        updateMenu.update(menuRequest);
        menuRepository.save(updateMenu);

        return updateMenu.toResponse();
    }
}
