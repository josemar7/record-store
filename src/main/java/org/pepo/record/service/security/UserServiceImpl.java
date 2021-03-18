package org.pepo.record.service.security;

import lombok.RequiredArgsConstructor;
import org.pepo.record.entity.security.Authority;
import org.pepo.record.entity.security.Role;
import org.pepo.record.entity.security.User;
import org.pepo.record.repository.security.AuthorityRepository;
import org.pepo.record.repository.security.RoleRepository;
import org.pepo.record.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void insertUsers() {
        Authority createTest = authorityRepository.save(Authority.builder()
                .permission("test.create")
                .build());
        Authority updateTest = authorityRepository.save(Authority.builder()
                .permission("test.update")
                .build());
        Authority readTest = authorityRepository.save(Authority.builder()
                .permission("test.read")
                .build());
        Authority deleteTest = authorityRepository.save(Authority.builder()
                .permission("test.delete")
                .build());

        Role adminRole = roleRepository.save(Role.builder()
                .name("ADMIN")
                .authorities(Set.of(createTest, updateTest, readTest, deleteTest))
                .build());
        Role customerRole = roleRepository.save(Role.builder()
                .name("CUSTOMER")
                .authorities(Set.of(readTest))
                .build());
        Role userRole = roleRepository.save(Role.builder()
                .name("USER")
                .authorities(Set.of(readTest))
                .build());

        userRepository.save(User.builder()
                .username("spring")
                .password(passwordEncoder.encode("pepo"))
                .role(adminRole)
                .build());

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .role(userRole)
                .build());

        userRepository.save(User.builder()
                .username("scott")
                .password(passwordEncoder.encode("tiger"))
                .role(customerRole)
                .build());
    }
}
