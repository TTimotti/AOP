package boot.demo.aop.application.rms.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AOPCode {
    ROLE_USER(1, "ROLE_USER"),
    ROLE_ADMIN(99, "ROLE_ADMIN");

    private final long code;
    private final String name;
}
