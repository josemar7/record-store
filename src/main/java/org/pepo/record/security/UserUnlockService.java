package org.pepo.record.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pepo.record.entity.security.User;
import org.pepo.record.repository.security.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserUnlockService {

    private final UserRepository userRepository;

    @Scheduled(fixedRate = 300000)
    public void unlockAccounts() {
        log.debug("Running unlock accounts");
        List<User> lockedUsers = userRepository.findAllByAccountNonLockedAndLastModifiedDateIsBefore(false,
                Timestamp.valueOf(LocalDateTime.now().minusSeconds(30)));
        if (!lockedUsers.isEmpty()) {
            log.debug("Locked accounts found, unlocking...");
            lockedUsers.forEach(user -> user.setAccountNonLocked(true));
            userRepository.saveAll(lockedUsers);
        }
    }

}
