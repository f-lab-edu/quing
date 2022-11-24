package flab.quing.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreManagerRequest {

    private String loginId;

    private String name;

    private String password;

    private String phoneNumber;

    private long storeId;

}
