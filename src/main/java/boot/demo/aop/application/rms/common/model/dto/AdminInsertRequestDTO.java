package boot.demo.aop.application.rms.common.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminInsertRequestDTO {
    private String username;
    private long roleCode;
}
