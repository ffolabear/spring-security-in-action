package com.example.ssiach11.ex1.service;

import com.example.ssiach11.ex1.entity.Otp;
import com.example.ssiach11.ex1.entity.User;
import com.example.ssiach11.ex1.repository.OtpRepository;
import com.example.ssiach11.ex1.repository.UserRepository;
import com.example.ssiach11.ex1.util.GenerateCodeUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        Optional<User> optionalUser = userRepository.findUserByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User findUser = optionalUser.get();
            if (passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
                renewOtp(findUser);
            } else {
                //암호가 틀릴 경우
                throw new BadCredentialsException("Bad credentials.");
            }
        } else {
            //사용자가 없을 경우
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    private void renewOtp(User user) {
        String code = GenerateCodeUtil.generateCode();
        Optional<Otp> findOtp = otpRepository.findOtpByUsername(user.getUsername());
        if (findOtp.isPresent()) {
            Otp otp = findOtp.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(user.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otpToValidate) {
        System.out.println("===============================================");
        System.out.println("UserService.check");
        System.out.println(otpToValidate);
        System.out.println("===============================================");
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
        System.out.println("===============================================");
        System.out.println("UserService.check");
        System.out.println(userOtp.get());
        System.out.println("===============================================");
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }

}
