package boot.demo.aop.application.rms.common.repository;

import boot.demo.aop.application.rms.common.model.entity.TbReportMst;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbReportMstRepository {

    int insertTbReportMst(TbReportMst newReportMst);
}
