package boot.demo.aop.main;

import boot.demo.aop.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class MainRestController {

    private final MainService mainService;

    @PostMapping("/hello")
    public ResponseEntity<String> hello(@Valid UserDTO userDTO) {

        return ResponseEntity.ok(mainService.hello(userDTO));
    }
}
