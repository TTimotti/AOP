package boot.demo.aop.common.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbUserRole {
    private long id;
    private String userId;
    private long roleCode;
}
