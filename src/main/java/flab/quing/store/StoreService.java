package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;

import java.util.List;

public interface StoreService {

    List<StoreResponse> getStoreList(String name);

    StoreResponse addStore(StoreRequest storeRequest);

    StoreResponse getStore(long storeId);

    StoreResponse updateStore(StoreRequest storeRequest);

    StoreResponse hideStore(long storeId);

    List<MenuResponse> getMenuList(long storeId);

    MenuResponse addMenu(MenuRequest menuRequest);

    MenuResponse hideMenu(long menuId);

    MenuResponse updateMenu(MenuRequest menuRequest);
}

