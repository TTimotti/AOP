package boot.demo.aop.repository;

import boot.demo.aop.model.TbUserMst;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    int insertUserMst(TbUserMst userMst);

}
