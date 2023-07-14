package com.example.sofiyauserservice.service;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.user.UserState;
import com.example.sofiyauserservice.repository.RoleRepository;
import com.example.sofiyauserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Transactional
    public UserEntity save(UserCreatDto userCreatDto) {
        checkUserEmail(userCreatDto.getEmail());
        UserEntity userEntity = modelMapper.map(userCreatDto, UserEntity.class);
        userEntity.setVerificationCode(generateVerificationCode());
        userEntity.setState(UserState.UNVERIFIED);
       return userRepository.save(userEntity);
    }

    private String generateVerificationCode() {
        Random random = new Random(100000);
        return String.valueOf(random.nextInt(1000000));
    }

    private void checkUserEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("email already exists");
        }
    }


}
