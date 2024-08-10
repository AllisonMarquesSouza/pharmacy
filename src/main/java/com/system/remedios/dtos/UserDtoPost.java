package com.system.remedios.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoPost {
    @NotBlank(message = "the name cannot be empty")
    private String username;
    @NotBlank(message = "the password cannot be empty")
    private String password;
}
