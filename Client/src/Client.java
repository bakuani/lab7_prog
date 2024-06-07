import OtherClasses.InputedCommand;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Чтобы работать с коллекцией нужно войти или зарегестрироваться.");
        InputedCommand input = new InputedCommand();
        input.input();
    }
}