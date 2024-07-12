package boot.demo.aop.application.rms.common.service;

import boot.demo.aop.application.rms.common.annotation.Loggable;
import boot.demo.aop.exception.AOPException;
import boot.demo.aop.exception.ErrorCode;
import boot.demo.aop.application.rms.common.model.AOPCode;
import boot.demo.aop.application.rms.common.model.dto.SignUpRequestDTO;
import boot.demo.aop.application.rms.common.model.dto.UserDetailsResponseDTO;
import boot.demo.aop.application.rms.common.model.entity.TbRole;
import boot.demo.aop.application.rms.common.model.entity.TbUserMst;
import boot.demo.aop.application.rms.common.model.entity.TbUserRole;
import boot.demo.aop.application.rms.common.repository.TbRoleRepository;
import boot.demo.aop.application.rms.common.repository.TbUserMstRepository;
import boot.demo.aop.application.rms.common.repository.TbUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbRoleRepository tbRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Loggable
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbUserMst userMst = tbUserMstRepository.getUserMstById(username);
        List<TbUserRole> userRoles = tbUserRoleRepository.getUserRoleByUserId(username);
        if(userMst == null) throw new UsernameNotFoundException("User not found");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (TbUserRole userRole : userRoles) {
            TbRole role = tbRoleRepository.getRoleByCode(userRole.getRoleCode());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return UserDetailsResponseDTO.builder()
                .username(userMst.getUserId())
                .password(userMst.getPassword())
                .authorities(authorities)
                .build();
    }

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
                .name(signUpRequestDTO.getName())
                .email(null)
                .build();

        TbUserRole newUserRole = TbUserRole.builder()
                .userId(signUpRequestDTO.getUsername())
                .roleCode(AOPCode.ROLE_USER.getCode())
                .build();


        tbUserMstRepository.insertTbUserMst(newUserMst);
        tbUserRoleRepository.insertTbUserRole(newUserRole);
    }
}
