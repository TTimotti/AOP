package boot.demo.aop.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequestDTO {
    @NotEmpty(message = "아이디를 입력하세요")
    private String username;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;
    @NotEmpty(message = "이름을 입력하세요")
    private String name;
}
