package boot.demo.aop.service;

import boot.demo.aop.aop.annotation.Loggable;
import boot.demo.aop.model.dto.UserDTO;
import boot.demo.aop.model.entity.TbRole;
import boot.demo.aop.model.entity.TbUserInfo;
import boot.demo.aop.model.entity.TbUserMst;
import boot.demo.aop.model.entity.TbUserRole;
import boot.demo.aop.repository.TbRoleRepository;
import boot.demo.aop.repository.TbUserInfoRepository;
import boot.demo.aop.repository.TbUserMstRepository;
import boot.demo.aop.repository.TbUserRoleRepository;
import lombok.Locked;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserInfoRepository tbUserInfoRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbRoleRepository tbRoleRepository;

    @Override
    @Loggable
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("+++ USER LOGIN +++");
        TbUserMst userMst = tbUserMstRepository.getUserMstById(username);
        TbUserInfo userInfo = tbUserInfoRepository.getUserInfoById(username);
        List<TbUserRole> userRoles = tbUserRoleRepository.getUserRoleByUserId(username);
        if(userMst == null || userInfo == null) throw new UsernameNotFoundException("User not found");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (TbUserRole userRole : userRoles) {
            TbRole role = tbRoleRepository.getRoleByCode(userRole.getRoleCode());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return UserDTO.builder()
                .username(userMst.getUserId())
                .password(userMst.getPassword())
                .authorities(authorities)
                .build();
    }
}
