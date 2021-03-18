package org.pepo.record.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingTests {

    static final String PASSWORD = "password";

    @Test
    void hashingExample() {
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        String salted = PASSWORD + "ThisIsMySALTVALUE";
        System.out.println(DigestUtils.md5DigestAsHex(salted.getBytes()));
    }

    @Test
    void testNoOp() {
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
        System.out.println(noOp.encode(PASSWORD));
    }

    @Test
    void testLdap() {
        PasswordEncoder ldap = new LdapShaPasswordEncoder();
        String encodedPw = ldap.encode(PASSWORD);
        System.out.println(ldap.encode(PASSWORD));
        System.out.println(ldap.encode(PASSWORD));
        System.out.println(ldap.encode("tiger"));
        assertTrue(ldap.matches(PASSWORD, encodedPw));
    }

    @Test
    void testSha256() {
        PasswordEncoder sha256 = new StandardPasswordEncoder();
        System.out.println(sha256.encode(PASSWORD));
        System.out.println(sha256.encode(PASSWORD));
    }

    @Test
    void testBcrypt() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        System.out.println(bcrypt.encode(PASSWORD));
        System.out.println(bcrypt.encode(PASSWORD));
        System.out.println(bcrypt.encode("pepo"));
        System.out.println(bcrypt.encode("hendi-secret"));
    }

    @Test
    void testBcrypt15() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
        System.out.println(bcrypt.encode(PASSWORD));
        System.out.println(bcrypt.encode(PASSWORD));
        System.out.println(bcrypt.encode("tiger"));
    }

}
