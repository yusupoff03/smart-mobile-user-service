package com.example.sofiyauserservice.service.verificationcode;

import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;

public interface GenerateVerificationCode {
    VerificationCode generateVerificationCode(UserEntity user);
}