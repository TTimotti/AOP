package boot.demo.aop.application.rms.user.service;

import boot.demo.aop.application.rms.common.annotation.Loggable;
import boot.demo.aop.application.rms.common.model.dto.AdminListResponseDTO;
import boot.demo.aop.application.rms.common.model.entity.TbUserMst;
import boot.demo.aop.application.rms.common.repository.TbRoleRepository;
import boot.demo.aop.application.rms.common.repository.TbUserMstRepository;
import boot.demo.aop.application.rms.common.repository.TbUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbRoleRepository tbRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Loggable
    public List<AdminListResponseDTO> getAll() {
        List<AdminListResponseDTO> result = new ArrayList<>();
        List<TbUserMst> adminUserList = tbUserMstRepository.getAllAdmin();
        for (TbUserMst user : adminUserList) {
            List<String> roleList = tbRoleRepository.getAllRoleNameByUserId(user.getUserId());
            result.add(
                    AdminListResponseDTO.builder()
                            .id(user.getUserId())
                            .name(user.getName())
                            .roleList(roleList)
                            .build()
            );
        }

        return result;
    }
}
