package com.roima.exammanagement.auth;

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
}
