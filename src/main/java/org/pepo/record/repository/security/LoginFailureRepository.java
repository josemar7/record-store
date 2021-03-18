package org.pepo.record.repository.security;

import org.pepo.record.entity.security.LoginFailure;
import org.pepo.record.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LoginFailureRepository extends JpaRepository<LoginFailure, Integer> {

    List<LoginFailure> findAllByUserAndCreatedDateIsAfter(User user, Timestamp timestamp);

}
