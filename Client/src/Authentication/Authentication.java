package Authentication;

import java.util.Scanner;

/**
 * получение данных для регистрации или входа
 */
public class Authentication {
    private static String username;
    private static String password;

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String inputCredentials() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите логин: ");
        username = in.nextLine();
        System.out.println("Введите пароль: ");
        password = in.nextLine();
        return username+","+password;
    }

}
