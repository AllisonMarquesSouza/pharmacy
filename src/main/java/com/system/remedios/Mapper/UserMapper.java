package com.system.remedios.Mapper;

import com.system.remedios.domain.Usuario;
import com.system.remedios.requests.UserPostRequestBody;
import com.system.remedios.requests.UserPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public abstract class UserMapper {

        public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
        public abstract Usuario toUsuario(UserPostRequestBody userPostRequestBody);
        public abstract Usuario toUsuario(UserPutRequestBody userPutRequestBody);

}

