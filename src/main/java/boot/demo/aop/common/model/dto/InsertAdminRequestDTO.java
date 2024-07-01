package boot.demo.aop.common.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertAdminRequestDTO {
    private String username;
    private long roleCode;
}
