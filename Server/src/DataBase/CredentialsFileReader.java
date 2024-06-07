package DataBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CredentialsFileReader {

    /**
     * получение данных для входа бд на гелиосе
     * @param filename файл, из которого читать данные
     * @return данные
     */
    public String getCredentials(String filename) {
        Scanner credentials = null;
        try {
            credentials = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            System.exit(-1);
        }

        try {
            String username = credentials.nextLine().trim();
            String password = credentials.nextLine().trim();
            return username + ' ' + password;
        } catch (NoSuchElementException e) {
            System.err.println("Не найдены данные для входа в файле");
            System.exit(-1);

        }

        return null;
    }
}
