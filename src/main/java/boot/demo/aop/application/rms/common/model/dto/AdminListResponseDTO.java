package boot.demo.aop.application.rms.common.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdminListResponseDTO {
    private String id;
    private String name;
    private List<String> roleList;

}
