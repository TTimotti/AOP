package boot.demo.aop.application.rms.common.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbUserMst {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String email;
}
