package boot.demo.aop.service;

import boot.demo.aop.aop.annotation.Loggable;
import boot.demo.aop.model.TbUserMst;
import boot.demo.aop.model.dto.UserDTO;
import boot.demo.aop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Loggable
    public UserDTO insertUserMst(UserDTO userDTO) {
        TbUserMst newUserMst = TbUserMst.modelFromDto(userDTO);
        int insertRows = userRepository.insertUserMst(newUserMst);
        log.info("INSERT ROWS: {}", insertRows);
        return TbUserMst.dtoFromModel(newUserMst);
    }
}
