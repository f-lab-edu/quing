package flab.quing.store.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@Builder
public class StoreRequest {

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

    private String pageLink;
}
