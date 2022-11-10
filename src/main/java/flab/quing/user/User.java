package flab.quing.user;

import flab.quing.entity.BaseEntity;
import flab.quing.user.dto.UserResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Setter(AccessLevel.NONE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User extends BaseEntity {

    private String name;

    private String phoneNumber;

    public UserResponse toResponse() {
        UserResponse userResponse = UserResponse.builder()
                .userId(getId())
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
        return userResponse;
    }

}

