package boot.demo.aop.application.rms.common.model.dto;

import boot.demo.aop.application.rms.common.model.entity.TbReportMst;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportInsertRequestDTO {
    private String title;
    private String author;
    private String content;

    public TbReportMst toEntity() {
        return TbReportMst.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
