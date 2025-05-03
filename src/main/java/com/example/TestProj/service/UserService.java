package com.example.TestProj.service;

import com.example.TestProj.dto.RegisterRequest;
import com.example.TestProj.entity.User;

public interface UserService {
    User registerUser(RegisterRequest request);
}
