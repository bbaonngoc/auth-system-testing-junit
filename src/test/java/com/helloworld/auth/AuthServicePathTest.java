package com.helloworld.auth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthServicePathTest {

    AuthService auth = new AuthService();

    @Test
    void testLoginWrongPasswordLessThan5() {
        auth.register("user123", "password123");

        for (int i = 0; i < 4; i++) {
            assertEquals("WRONG_PASSWORD", auth.login("user123", "wrong"));
        }
    }

    @Test
    void testLoginLockAt5thAttempt() {
        auth.register("user123", "password123");

        for (int i = 0; i < 4; i++) {
            auth.login("user123", "wrong");
        }

        // lần thứ 5 → phải lock
        assertEquals("ACCOUNT_LOCKED", auth.login("user123", "wrong"));
    }

    @Test
    void testLoginAfterAccountLocked() {
        auth.register("user123", "password123");

        for (int i = 0; i < 5; i++) {
            auth.login("user123", "wrong");
        }

        // đã bị khóa → dù đúng pass vẫn không login được
        assertEquals("ACCOUNT_LOCKED", auth.login("user123", "password123"));
    }

    @Test
    void testRegisterBoundaryValues() {
        // username = 6 ký tự (min hợp lệ)
        assertTrue(auth.register("user12", "password123"));

        // password = 6 ký tự (min hợp lệ)
        assertTrue(auth.register("user34", "pass12"));
    }
}