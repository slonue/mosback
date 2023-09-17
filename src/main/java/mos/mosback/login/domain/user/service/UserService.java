package mos.mosback.login.domain.user.service;

import mos.mosback.login.domain.user.Role;
import mos.mosback.login.domain.user.User;
import mos.mosback.login.domain.user.dto.UserSignUpDto;
import mos.mosback.login.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .nickname(userSignUpDto.getNickname())
                .duration(userSignUpDto.getDuration())
                .message(userSignUpDto.getMessage())
                .company(userSignUpDto.getCompany())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }

//    public boolean isEmailDuplicate(String email) {
//        Optional<User> findUser = userRepository.findByEmail(email);
//        return findUser != null;
//    }
//    public boolean comparePassword(String email,String password) {
//        Optional<User> findUser = userRepository.findByEmail(email);
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        boolean isMatched = passwordEncoder.matches(password, userRepository.findByPassword());
//
//        if(isMatched) {
//            return true;
//        }
//        else{
//            return false;
//        }
//    }


}


