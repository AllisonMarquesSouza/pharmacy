package com.system.remedios.util;

import com.system.remedios.domain.Usuario;

public class UserCreator {
    public static Usuario createValidUser(){
        return Usuario.builder()
                .username("theCrazy")
                .password("123456")
                .build();
    }
    public static Usuario createUserToBeSaved(){
        return Usuario.builder()
                .id(1L)
                .username("theCrazy")
                .password("123456")
                .build();
    }
    public static Usuario replaceUser(){
        return Usuario.builder()
                .id(2L)
                .username("allison")
                .password("1234")
                .build();
    }


}
