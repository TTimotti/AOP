package boot.demo.aop.application.rms.common.repository;

import boot.demo.aop.application.rms.common.model.entity.TbRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbRoleRepository {

    TbRole getRoleByCode(long roleCode);

    List<TbRole> getAllRole();

    List<String> getAllRoleNameByUserId(String userId);
}
