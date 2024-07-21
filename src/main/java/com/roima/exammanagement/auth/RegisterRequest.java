package com.roima.exammanagement.auth;

import com.roima.exammanagement.model.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor

public class RegisterRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private Boolean isActive;

    private Role role;
}
