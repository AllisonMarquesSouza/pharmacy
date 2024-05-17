package com.system.remedios.requests;

import com.system.remedios.util.UserCreator;


public class UserPostRequestCreator {
    public static UserPostRequestBody userPostRequestCreator() {
        return UserPostRequestBody.builder()
                .username(UserCreator.createUserToBeSaved().getUsername())
                .password(UserCreator.createUserToBeSaved().getPassword())
                .build();
    }
}
