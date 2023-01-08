package flab.quing.store;

import flab.quing.store.dto.MenuRequest;
import flab.quing.store.dto.MenuResponse;
import flab.quing.store.dto.StoreAddRequest;
import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;
import flab.quing.user.dto.StoreManagerResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getStoreList() {
        List<StoreResponse> storeList = storeService.getStoreList();
        return ResponseEntity.ok(storeList);
    }

    @PostMapping
    public ResponseEntity<StoreResponse> addStore(
            @Parameter(hidden = true)
            @SessionAttribute(name = "AUTH_STORE")
            StoreManagerResponse storeManagerResponse,
            @RequestBody StoreAddRequest storeAddRequest
    ) {
        StoreRequest storeRequest = StoreRequest.of(storeManagerResponse.getId(), storeAddRequest);
        StoreResponse storeResponse = storeService.addStore(storeRequest);

        return ResponseEntity.ok(storeResponse);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse> getStore(@PathVariable("storeId") Long storeId) {
        StoreResponse storeResponse = storeService.getStore(storeId);

        return ResponseEntity.ok(storeResponse);
    }

    @PatchMapping("/{storeId}")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable("storeId") Long storeId, @RequestBody StoreRequest storeRequest) {
        StoreResponse storeResponse = storeService.updateStore(storeId, storeRequest);

        return ResponseEntity.ok(storeResponse);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<StoreResponse> hideStore(@PathVariable("storeId") Long storeId) {
        StoreResponse storeResponse = storeService.hideStore(storeId);

        return ResponseEntity.ok(storeResponse);
    }

    @GetMapping("/menu-list")
    public ResponseEntity<List<MenuResponse>> getMenuList(@RequestParam("storeId") Long storeId) {
        List<MenuResponse> menuList = storeService.getMenuList(storeId);

        return ResponseEntity.ok(menuList);
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuResponse> addMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = storeService.addMenu(menuRequest);

        return ResponseEntity.ok(menuResponse);
    }

    @PatchMapping("/{menuId}")
    public ResponseEntity<MenuResponse> hideMenu(@PathVariable("menuId") Long menuId) {
        MenuResponse menuResponse = storeService.hideMenu(menuId);

        return ResponseEntity.ok(menuResponse);
    }

    @PatchMapping("/menu")
    public ResponseEntity<MenuResponse> updateMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = storeService.updateMenu(menuRequest);

        return ResponseEntity.ok(menuResponse);
    }


}
