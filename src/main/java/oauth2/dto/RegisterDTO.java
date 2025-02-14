package oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {
    private String clientName;
    private String redirectUris;
    private String postLogoutRedirectUris;
    private String scopes;
}
