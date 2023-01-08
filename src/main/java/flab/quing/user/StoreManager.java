package flab.quing.user;

import flab.quing.entity.BaseEntity;
import flab.quing.store.Store;
import flab.quing.user.dto.StoreManagerResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StoreManager extends BaseEntity {

    private String loginId;

    private String encryptedPassword;

    private String phoneNumber;

    private String name;

    @OneToOne
    @Setter
    private Store store;

    public StoreManagerResponse toResponse() {
        Long storeId = null;
        if (store != null) {
            storeId = store.getId();
        }
        StoreManagerResponse storeManagerResponse = StoreManagerResponse.builder()
                .id(this.getId())
                .loginId(loginId)
                .phoneNumber(phoneNumber)
                .name(name)
                .storeId(storeId)
                .build();

        return storeManagerResponse;
    }

}
