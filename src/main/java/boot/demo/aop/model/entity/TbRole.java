package boot.demo.aop.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbRole {
    private long id;
    private long code;
    private String name;
}
