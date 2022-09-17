package flab.quing.store.dto;

import lombok.Getter;

@Getter
public class StoreResponse {

    private Long id;
    private String name;
    private String phoneNumber;
    private String openStatus;
    private String openHours;
    private String address;
    private String pageLink;

    public StoreResponse(Long id, String name, String phoneNumber, String openStatus, String openHours, String address, String pageLink) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.openStatus = openStatus;
        this.openHours = openHours;
        this.address = address;
        this.pageLink = pageLink;
    }
}
