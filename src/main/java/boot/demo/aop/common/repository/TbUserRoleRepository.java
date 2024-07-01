package boot.demo.aop.common.repository;

import boot.demo.aop.common.model.entity.TbUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbUserRoleRepository {

    List<TbUserRole> getUserRoleByUserId(String userId);

    int insertTbUserRole(TbUserRole newUserRole);
}
