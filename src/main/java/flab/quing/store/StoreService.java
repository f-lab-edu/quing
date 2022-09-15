package flab.quing.store;

import flab.quing.store.dto.AddMenuRequest;
import flab.quing.store.dto.AddMenuResponse;
import flab.quing.store.dto.MenuResponse;

import java.util.List;

public interface StoreService {

    List<Store> getStoreList();

    List<MenuResponse> getMenuList(Long storeId);

    AddMenuResponse add(AddMenuRequest menu);
    //200 ok, 등록된 Menu의 정보, dto, HttpResponse<dto>, AddMenuResponse
}
