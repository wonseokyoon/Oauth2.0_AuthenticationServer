package oauth2.controller;

import lombok.RequiredArgsConstructor;
import oauth2.domain.RegisterEntity;
import oauth2.dto.RegisterDTO;
import oauth2.service.RegisterService;
import oauth2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<RegisterEntity> register(RegisterDTO registerDTO) {
        RegisterEntity register=registerService.register(registerDTO);
        return ResponseEntity.ok(register);
    }
}
