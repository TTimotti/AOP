package boot.demo.aop.application.rms.common.repository;

import boot.demo.aop.application.rms.common.model.entity.TbUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbUserRoleRepository {

    List<TbUserRole> getUserRoleByUserId(String userId);

    int insertTbUserRole(TbUserRole newUserRole);
}
