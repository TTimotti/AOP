package boot.demo.aop.main;

import boot.demo.aop.aop.annotation.Loggable;
import boot.demo.aop.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Loggable
    public String hello(UserDTO userDTO) {

        return "Hello MR." + userDTO.getUsername();
    }
}
