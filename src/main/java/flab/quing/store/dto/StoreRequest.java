package flab.quing.store.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreRequest {

    private long storeManagerId;

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

    private String pageLink;

    public static StoreRequest of(long storeManagerId, StoreAddRequest storeAddRequest) {
        return StoreRequest.builder()
                .storeManagerId(storeManagerId)
                .name(storeAddRequest.getName())
                .phoneNumber(storeAddRequest.getPhoneNumber())
                .openStatus(storeAddRequest.getOpenStatus())
                .openHours(storeAddRequest.getOpenHours())
                .address(storeAddRequest.getAddress())
                .pageLink(storeAddRequest.getPageLink())
                .build();
    }
}
