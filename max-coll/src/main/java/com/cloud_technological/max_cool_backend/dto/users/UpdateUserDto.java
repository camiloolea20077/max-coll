package com.cloud_technological.max_cool_backend.dto.users;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private Long id;
    private Long companyId;
    private Long roleId;
    private String name;
    private String username;
    private String password;
    private String email;
    private BigDecimal active; // 1/0
}
