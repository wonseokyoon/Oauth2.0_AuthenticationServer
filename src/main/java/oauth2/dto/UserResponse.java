package oauth2.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String username;
    private String role;
    private String nickname;
    private String email;
}
