package flab.quing.store;

import java.util.List;

public interface StoreService {
    //storeService.getList();

    List<Store> getStoreList();

    List<Menu> getMenuList(Store store);

    Long addMenu(Menu menu);
}
