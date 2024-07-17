package boot.demo.aop.application.rms.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ROLE_USER(1, "ROLE_USER"),
    ROLE_ADMIN(9, "ROLE_ADMIN");

    private final long code;
    private final String name;
}
