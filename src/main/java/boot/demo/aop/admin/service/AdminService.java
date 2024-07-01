package boot.demo.aop.admin.service;

import boot.demo.aop.common.annotation.Loggable;
import boot.demo.aop.common.exception.AOPException;
import boot.demo.aop.common.exception.ErrorCode;
import boot.demo.aop.common.model.dto.AdminListResponseDTO;
import boot.demo.aop.common.model.dto.InsertAdminRequestDTO;
import boot.demo.aop.common.model.entity.TbRole;
import boot.demo.aop.common.model.entity.TbUserMst;
import boot.demo.aop.common.repository.TbRoleRepository;
import boot.demo.aop.common.repository.TbUserMstRepository;
import boot.demo.aop.common.repository.TbUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbRoleRepository tbRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Loggable
    @Transactional
    public void insertAdmin(InsertAdminRequestDTO insertAdminRequestDTO) {
        TbUserMst user = tbUserMstRepository.getUserMstById(insertAdminRequestDTO.getUsername());
        if (user == null) {
            throw new AOPException(ErrorCode.USER_NOT_FOUND);
        }

    }

    public List<TbRole> getRoleList() {
        return tbRoleRepository.getAllRole();
    }

    @Loggable
    public List<AdminListResponseDTO> getAllAdmin() {
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
