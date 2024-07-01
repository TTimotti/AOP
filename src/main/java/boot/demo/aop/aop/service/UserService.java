package boot.demo.aop.aop.service;

import boot.demo.aop.common.repository.TbUserMstRepository;
import boot.demo.aop.common.repository.TbUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final PasswordEncoder passwordEncoder;


}
