package ru.job4j.early;

public class PasswordValidator {
    public static boolean checkUpperCase(String password) {
        char[] chars = password.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (Character.isUpperCase(chars[index])) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLowerCase(String password) {
        char[] chars = password.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (Character.isLowerCase(chars[index])) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDigitCase(String password) {
        char[] chars = password.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (Character.isDigit(chars[index])) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkSpecialSymbolCase(String password) {
        char[] chars = password.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (!Character.isLetterOrDigit(chars[index])) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkForbiddenCase(String password) {
        String[] words = new String[]{"qwerty", "12345", "password", "admin", "user"};
        for (String word : words) {
            if (password.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() > 32 || password.length() < 8) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        if (!checkUpperCase(password)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!checkLowerCase(password)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!checkDigitCase(password)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!checkSpecialSymbolCase(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (checkForbiddenCase(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, "
                    + "password, admin, user");
        }
        return password;
    }
}