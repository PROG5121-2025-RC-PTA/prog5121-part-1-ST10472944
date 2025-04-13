/*
 * Click nbfs://n/*bhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/**
 * =======================================================
 * This program was developed with assistance from DeepSeek Chat (AI).  
 * Modifications and final implementation were done by me Makgai Thapelo.
 * 
 * Reference: DeepSeek (2024) DeepSeek Chat [AI model]. 
 *            Available at: https://deepseek.com 
 *            (Accessed: [06/ 04/ 2025]).
 * =======================================================
 */
package com.mycompany.registration_and_login_feature;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class Registration_and_Login_Feature {
    private Map<String, User> users;
    private User currentUser;

    public Registration_and_Login_Feature() {
        this.users = new HashMap<>();
        this.currentUser = null;
    }

    // User class to store user information
    private static class User {
        String firstName;
        String lastName;
        String username;
        String password;
        String phone;

        public User(String firstName, String lastName, String username, String password, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.phone = phone;
        }
    }

    // Check username format
    public boolean checkUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Check password complexity
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        if (!Pattern.compile("[A-Z]").matcher(password).find()) return false;
        if (!Pattern.compile("[0-9]").matcher(password).find()) return false;
        return Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find();
    }

    // Check phone number format
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+") && phone.substring(1).length() <= 10 && phone.substring(1).matches("\\d+");
    }

    // Register a new user
    public String registerUser(String firstName, String lastName, String username, String password, String phone) {
        StringBuilder message = new StringBuilder();

        // Validate username
        if (checkUsername(username)) {
            message.append("Username successfully captured\n");
        } else {
            message.append("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length\n");
        }

        // Validate password
        if (checkPasswordComplexity(password)) {
            message.append("Password successfully captured\n");
        } else {
            message.append("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character\n");
        }

        // Validate phone
        if (checkCellPhoneNumber(phone)) {
            message.append("Cell phone number successfully added\n");
        } else {
            message.append("Cell phone number incorrectly formatted or does not contain international code\n");
        }

        // If all validations passed, register user
        if (checkUsername(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(phone)) {
            users.put(username, new User(firstName, lastName, username, password, phone));
            message.append("User registered successfully!");
        } else {
            message.append("Registration failed - please correct the errors above.");
        }

        return message.toString();
    }

    // Login user
    public boolean loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    // Return login status message
    public String returnLoginStatus() {
        if (currentUser != null) {
            return "Welcome " + currentUser.firstName + ", " + currentUser.lastName + " it is great to see you again.";
        }
        return "Username or password incorrect please try again";
    }

    public static void main(String[] args) {
        Registration_and_Login_Feature system = new Registration_and_Login_Feature();

        // Registration 
        System.out.println("=== Registration ===");
        System.out.println(system.registerUser(
                "Thapelo",
                "Makgai",
                "Tp_12",
                "Passw0rd!",
                "+2774567890"
        ));

        // Login 
        System.out.println("\n=== Login ===");
        if (system.loginUser("Tp_12", "Passw0rd!")) {
            System.out.println(system.returnLoginStatus());
        } else {
            System.out.println(system.returnLoginStatus());
        }
    }
}