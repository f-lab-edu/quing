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
public class StoreAddRequest {

    private String name;

    private String phoneNumber;

    private String openStatus;

    private String openHours;

    private String address;

    private String pageLink;

}
