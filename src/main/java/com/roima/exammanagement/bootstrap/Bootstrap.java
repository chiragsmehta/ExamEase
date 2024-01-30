package com.roima.exammanagement.bootstrap;


import com.roima.exammanagement.model.Role;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class Bootstrap implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        var admin = User.builder()
                .name("admin")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(admin);

    }
}