package boot.demo.aop.repository;

import boot.demo.aop.model.entity.TbUserMst;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserMstRepository {

    int insertTbUserMst(TbUserMst userMst);
    TbUserMst getUserMstById(String userId);

}
