package boot.demo.aop.application.rms.common.model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TbReportMst {
    private long id;
    private String title;
    private String content;
    private String author;
    private String createDatetime;
    private String modifiedDatetime;
}
