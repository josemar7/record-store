package org.pepo.record.security;

import lombok.extern.slf4j.Slf4j;
import org.pepo.record.entity.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordStoreAuthenticationManager {

    public boolean userIdMatches(Authentication authentication, int userId) {
        User authenticatedUser = (User) authentication.getPrincipal();
        log.debug("Auth user id : " + authenticatedUser.getId() + " User id : " + userId);
        return authenticatedUser.getId() == userId;
    }
}
