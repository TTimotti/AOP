package boot.demo.aop.model;

import boot.demo.aop.model.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TbUserMst {
    private String userId;
    private String password;
    private String name;

    public static TbUserMst modelFromDto(UserDTO userDTO) {
        return TbUserMst.builder()
                .userId(userDTO.getUsername())
                .password(userDTO.getPassword())
                .name("테스트")
                .build();
    }

    public static UserDTO dtoFromModel(TbUserMst userMst) {
        return UserDTO.builder()
                .username(userMst.userId)
                .password(userMst.getPassword())
                .build();
    }
}
