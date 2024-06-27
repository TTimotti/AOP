package boot.demo.aop.repository;

import boot.demo.aop.model.entity.TbUserInfo;
import boot.demo.aop.model.entity.TbUserMst;
import boot.demo.aop.model.entity.TbUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserInfoRepository {

    int insertTbUserInfo(TbUserInfo newUserInfo);

    TbUserInfo getUserInfoById(String userId);
}
