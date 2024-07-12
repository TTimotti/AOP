package boot.demo.aop.application.rms.common.repository;

import boot.demo.aop.application.rms.common.model.entity.TbUserMst;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbUserMstRepository {

    int insertTbUserMst(TbUserMst userMst);
    TbUserMst getUserMstById(String userId);

    List<TbUserMst> getAllAdmin();
}
