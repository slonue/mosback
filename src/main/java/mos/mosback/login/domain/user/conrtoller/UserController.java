package mos.mosback.login.domain.user.conrtoller;

import lombok.RequiredArgsConstructor;
import mos.mosback.login.domain.user.dto.UserSignUpDto;
import mos.mosback.login.domain.user.repository.UserRepository;
import mos.mosback.login.domain.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private UserRepository userRepository;
    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }


    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }


}
