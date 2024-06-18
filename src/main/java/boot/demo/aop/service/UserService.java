package boot.demo.aop.service;

import boot.demo.aop.aop.annotation.Loggable;
import boot.demo.aop.model.TbUserMst;
import boot.demo.aop.model.dto.UserDTO;
import boot.demo.aop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Loggable
    public UserDTO insertUserMst(UserDTO userDTO) {
        TbUserMst newUserMst = TbUserMst.modelFromDto(userDTO);
        userRepository.insertUserMst(newUserMst);
        return TbUserMst.dtoFromModel(newUserMst);
    }
}
