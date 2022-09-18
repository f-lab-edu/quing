package flab.quing.store.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class StoreResponse {

    private long storeId;

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

    private String pageLink;

}
