/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration_and_login_feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationAndLoginFeatureTest {
    
    private Registration_and_Login_Feature system;
    
    @BeforeEach
    void setUp() {
        system = new Registration_and_Login_Feature();
    }

    // ===== Username Validation Tests =====
    @Test
    void testCheckUsername_Valid() {
        assertTrue(system.checkUsername("a_b"));      // 3 chars with underscore
        assertTrue(system.checkUsername("Tp_12"));  // 5 chars with underscore
    }

    @Test
    void testCheckUsername_Invalid() {
        assertFalse(system.checkUsername("abcd"));    // No underscore
        assertFalse(system.checkUsername("a_long")); // >5 chars
        assertFalse(system.checkUsername("Tp12"));   // No underscore
    }

    // ===== Password Complexity Tests =====
    @Test
    void testCheckPasswordComplexity_Valid() {
        assertTrue(system.checkPasswordComplexity("Pass@123"));  // 8+ chars, upper, number, special
        assertTrue(system.checkPasswordComplexity("Sec#4567"));  // Alternative special char
    }

    @Test
    void testCheckPasswordComplexity_Invalid() {
        assertFalse(system.checkPasswordComplexity("pass1234"));  // No uppercase
        assertFalse(system.checkPasswordComplexity("P@ss"));      // Too short (<8)
        assertFalse(system.checkPasswordComplexity("Password"));  // No number/special
    }

    // ===== Phone Number Validation Tests =====
    @Test
    void testCheckCellPhoneNumber_Valid() {
        assertTrue(system.checkCellPhoneNumber("+2712345678"));  // Standard international
        assertTrue(system.checkCellPhoneNumber("+2774123456"));  // Mobile international
    }

    @Test
    void testCheckCellPhoneNumber_Invalid() {
        assertFalse(system.checkCellPhoneNumber("0123456789"));   // Local format (should start with +)
        assertFalse(system.checkCellPhoneNumber("+271234567"));   // Too short (9 digits)
        assertFalse(system.checkCellPhoneNumber("+27123456789")); // Too long (11 digits)
        assertFalse(system.checkCellPhoneNumber("+2612345678"));  // Wrong country code
        assertFalse(system.checkCellPhoneNumber("+27abc56789"));  // Contains letters
    }

    // ===== Registration Tests =====
    @Test
    void testRegisterUser_Success() {
        String result = system.registerUser("Thapelo", "Makgai", "Tp_12", "Pass@123", "+2712345678");
        assertTrue(result.contains("User registered successfully!"));
    }

    @Test
    void testRegisterUser_InvalidUsername() {
        String result = system.registerUser("Thapelo", "Makgai", "Tp12", "Pass@123", "+2712345678");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    void testRegisterUser_InvalidPassword() {
        String result = system.registerUser("Thapelo", "Makgai", "Tp_12", "weak", "+2712345678");
        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    void testRegisterUser_InvalidPhone() {
        String result = system.registerUser("Thapelo", "Makgai", "Tp_12", "Pass@123", "0123456789");
        assertTrue(result.contains("Cell phone number incorrectly formatted"));
    }

    // ===== Login Tests =====
    @Test
    void testLoginUser_Success() {
        system.registerUser("Thapelo", "Makgai", "Tp_12", "Pass@123", "+2712345678");
        assertTrue(system.loginUser("Tp_12", "Pass@123"));
    }

    @Test
    void testLoginUser_Failure() {
        system.registerUser("Thapelo", "Makgai", "Tp_12", "Pass@123", "+2712345678");
        assertFalse(system.loginUser("Tp_12", "WrongPass"));
        assertFalse(system.loginUser("unknown", "Pass@123"));
    }

    // ===== Login Status Tests =====
    @Test
    void testReturnLoginStatus_Success() {
        system.registerUser("Thapelo", "Makgai", "Tp_12", "Pass@123", "+2712345678");
        system.loginUser("Tp_12", "Pass@123");
        assertTrue(system.returnLoginStatus().contains("Welcome Thapelo, Makgai"));
    }

    @Test
    void testReturnLoginStatus_Failure() {
        assertEquals("Username or password incorrect please try again", 
            system.returnLoginStatus());
    }
}