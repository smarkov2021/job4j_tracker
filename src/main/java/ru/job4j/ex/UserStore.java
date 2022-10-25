package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
           int rsl = -1;
           for (int i = 0; i < users.length; i++) {
               if (users[i].getUsername().equals(login)) {
                   rsl = i;
                   break;
               }
           }
           if (rsl == -1) {
               throw new UserNotFoundException("Пользователь не найден");
           }
           return users[rsl];

    }

    public static boolean validate(User user) throws UserInvalidException {
        if (user.getUsername().length() < 3) {
            throw new UserInvalidException("Имя содержит менее трех символов");
        }
        if (!user.isValid()) {
            throw new UserInvalidException("Пользователь не валидный");
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ui) {
            ui.printStackTrace();
        } catch (UserNotFoundException un) {
            un.printStackTrace();
        }

    }
}