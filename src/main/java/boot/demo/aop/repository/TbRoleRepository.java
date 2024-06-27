package boot.demo.aop.repository;

import boot.demo.aop.model.entity.TbRole;
import boot.demo.aop.model.entity.TbUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbRoleRepository {

    TbRole getRoleByCode(long roleCode);
}
