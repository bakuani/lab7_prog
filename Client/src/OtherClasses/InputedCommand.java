package OtherClasses;

import java.util.Scanner;


/**
 * класс для обработки входа данных в консоли
 */
public class InputedCommand {

    SendObject sendObject = new SendObject();
    private static String[] inputed_command;

    public static String[] getInputed_command() {
        return inputed_command;
    }

    /**
     * функция, обрабатывающая вход с консоли
     */
    public byte[] input() {
        while (true) {

            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            inputed_command = command.split(" ");
            if (inputed_command.length > 2) {
                System.out.println("Команда введена некорректно");
            } else if (inputed_command.length <= 2) {
                sendObject.sendObject();
            }
        }
    }
}
