package boot.demo.aop.repository;

import boot.demo.aop.model.entity.TbUserInfo;
import boot.demo.aop.model.entity.TbUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbUserRoleRepository {

    List<TbUserRole> getUserRoleByUserId(String userId);

    int insertTbUserRole(TbUserRole newUserRole);
}
