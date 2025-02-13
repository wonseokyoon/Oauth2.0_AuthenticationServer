package oauth2.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import oauth2.dto.UserDTO;
import oauth2.dto.UserResponse;
import oauth2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String joinPage(){
        return "joinPage";
    }

    @PostMapping("/join")
    public ResponseEntity<UserResponse> join(@ModelAttribute UserDTO dto){
        UserResponse response=userService.join(dto);
        return ResponseEntity.ok(response);
    }
}
