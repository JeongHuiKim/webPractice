package me.jeonghui.springbootlevel2.service;

import lombok.RequiredArgsConstructor;
import me.jeonghui.springbootlevel2.dto.AddUserRequest;
import me.jeonghui.springbootlevel2.entity.User;
import me.jeonghui.springbootlevel2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

}
