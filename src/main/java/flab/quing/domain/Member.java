package flab.quing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String password;
    private String phone;
}
