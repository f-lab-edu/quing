package flab.quing.store;

import flab.quing.entity.BaseEntity;
import flab.quing.store.dto.StoreRequest;
import flab.quing.store.dto.StoreResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store extends BaseEntity {

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

    private String pageLink;

    public StoreResponse toResponse() {
        StoreResponse storeResponse = StoreResponse.builder()
                .storeId(getId())
                .name(name)
                .phoneNumber(phoneNumber)
                .openStatus(openStatus)
                .openHours(openHours)
                .address(address)
                .pageLink(pageLink)
                .build();
        return storeResponse;
    }

    public void update(StoreRequest storeRequest) {
        name = storeRequest.getName();
        phoneNumber = storeRequest.getPhoneNumber();
        openStatus = storeRequest.getOpenStatus();
        openHours = storeRequest.getOpenHours();
        address = storeRequest.getAddress();
        pageLink = storeRequest.getPageLink();
    }

}
