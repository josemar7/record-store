package org.pepo.record.repository.security;

import org.pepo.record.entity.security.LoginSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Integer> {
}
