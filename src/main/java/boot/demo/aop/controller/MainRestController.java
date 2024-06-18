package boot.demo.aop.controller;

import boot.demo.aop.model.dto.UserDTO;
import boot.demo.aop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class MainRestController {

    private final UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<UserDTO> hello(@Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.insertUserMst(userDTO));
    }
}
