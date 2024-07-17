package boot.demo.aop.application.rms.admin.service;

import boot.demo.aop.application.rms.common.annotation.Loggable;
import boot.demo.aop.exception.AOPException;
import boot.demo.aop.exception.ErrorCode;
import boot.demo.aop.application.rms.common.model.dto.AdminListResponseDTO;
import boot.demo.aop.application.rms.common.model.dto.AdminInsertRequestDTO;
import boot.demo.aop.application.rms.common.model.entity.TbRole;
import boot.demo.aop.application.rms.common.model.entity.TbUserMst;
import boot.demo.aop.application.rms.common.repository.TbRoleRepository;
import boot.demo.aop.application.rms.common.repository.TbUserMstRepository;
import boot.demo.aop.application.rms.common.repository.TbUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbRoleRepository tbRoleRepository;

    @Loggable
    @Transactional
    public void insertAdmin(AdminInsertRequestDTO insertAdminRequestDTO) {
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
