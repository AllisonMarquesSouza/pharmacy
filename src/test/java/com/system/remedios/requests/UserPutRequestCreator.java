package com.system.remedios.requests;

import com.system.remedios.util.UserCreator;


public class UserPutRequestCreator {
    public static UserPutRequestBody userPutRequestCreator(){
        return UserPutRequestBody.builder()
                .username(UserCreator.createUserToBeSaved().getUsername())
                .build();
    }
}
