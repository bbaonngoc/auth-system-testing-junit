package com.helloworld.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthServiceStatementTest {

    AuthService auth = new AuthService();

    @Test
    void testRegisterValid() {
        assertTrue(auth.register("user123", "password123"));
    }

    @Test
    void testRegisterInvalidUsername() {
        assertFalse(auth.register("usr", "password123"));
    }

    @Test
    void testRegisterInvalidPassword() {
        assertFalse(auth.register("user123", "123"));
    }

    @Test
    void testRegisterDuplicate() {
        auth.register("user123", "password123");
        assertFalse(auth.register("user123", "password123"));
    }

    @Test
    void testLoginSuccess() {
        auth.register("user123", "password123");
        assertEquals("LOGIN_SUCCESS", auth.login("user123", "password123"));
    }

    @Test
    void testUserNotFound() {
        assertEquals("USER_NOT_FOUND", auth.login("abc", "123"));
    }

    @Test
    void testWrongPassword() {
        auth.register("user123", "password123");
        assertEquals("WRONG_PASSWORD", auth.login("user123", "wrong"));
    }

    @Test
    void testAccountLock() {
        auth.register("user123", "password123");

        for (int i = 0; i < 5; i++) {
            auth.login("user123", "wrong");
        }

        assertEquals("ACCOUNT_LOCKED", auth.login("user123", "password123"));
    }
}
