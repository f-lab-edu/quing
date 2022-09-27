package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;

import java.util.List;

public interface StoreService {

    List<StoreResponse> findByStoreName(String name);

    StoreResponse get(Long storeId);

    StoreResponse update(StoreRequest storeRequest);

    List<MenuResponse> getMenuList(Long storeId);

    MenuResponse add(MenuRequest menu);

    MenuResponse hide(Long menuId);

    MenuResponse update(MenuRequest menuRequest);
}
