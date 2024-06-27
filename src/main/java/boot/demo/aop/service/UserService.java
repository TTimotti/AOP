package boot.demo.aop.service;

import boot.demo.aop.aop.annotation.Loggable;
import boot.demo.aop.exception.AOPException;
import boot.demo.aop.exception.ErrorCode;
import boot.demo.aop.model.Code;
import boot.demo.aop.model.entity.TbUserInfo;
import boot.demo.aop.model.entity.TbUserMst;
import boot.demo.aop.model.entity.TbUserRole;
import boot.demo.aop.model.dto.SignUpRequestDTO;
import boot.demo.aop.repository.TbUserInfoRepository;
import boot.demo.aop.repository.TbUserMstRepository;
import boot.demo.aop.repository.TbUserRoleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbUserInfoRepository tbUserInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Loggable
    @Transactional
    public void insertUserMst(SignUpRequestDTO signUpRequestDTO) {
        TbUserMst user = tbUserMstRepository.getUserMstById(signUpRequestDTO.getUsername());
        if (user != null) {
            throw new AOPException(ErrorCode.ALREADY_HAS_USER);
        }

        TbUserMst newUserMst = TbUserMst.builder()
                .userId(signUpRequestDTO.getUsername())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .build();

        TbUserInfo newUserInfo = TbUserInfo.builder()
                .userId(signUpRequestDTO.getUsername())
                .name(signUpRequestDTO.getName())
                .build();
        // 기본은 ROLE_USER. 관리자의 경우 DB로 등록하거나 관리자 페이지에서 유저를 관리자로 등록 가능하도록 구현 예정.
        TbUserRole newUserRole = TbUserRole.builder()
                .userId(signUpRequestDTO.getUsername())
                .roleCode(Code.ROLE_USER.getCode())
                .build();


        tbUserMstRepository.insertTbUserMst(newUserMst);
        tbUserInfoRepository.insertTbUserInfo(newUserInfo);
        tbUserRoleRepository.insertTbUserRole(newUserRole);
    }

}
