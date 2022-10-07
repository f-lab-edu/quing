package flab.quing.user;

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
public class StoreManager extends BaseEntity {

    private String loginId;

    private String encryptedPassword;

    private String phoneNumber;

    private String name;
}
