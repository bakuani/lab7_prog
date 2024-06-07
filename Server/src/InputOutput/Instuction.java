package InputOutput;

import Commands.Command;
import Commands.CommandList;
import OtherClasses.Person;
import OtherClasses.Request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * класс с условиями  выполения команд
 */
public class Instuction {
    public static int cycle;
    private ArrayList<String> commandsWithArgs = new ArrayList<>(List.of("remove_by_id", "update"));
    private ArrayList<String> commandsWithLengthTwo = new ArrayList<>(List.of("filter_starts_with_name", "execute_script", "login", "register"));

    /**
     * функция, выполняющая команды после ввода
     *
     * @param
     * @return
     */
    public Response instruction(Request request){
        try {
            HashMap<String, Command> withoutArgs = HashMaps.getWithoutArgs();
            HashMap<String, Command> withArgs = HashMaps.getWithArgs();
            String[] inputed_command = request.getCommand();
            if (inputed_command.length == 1 && withoutArgs.containsKey(inputed_command[0])) {
                return withoutArgs.get(inputed_command[0]).execute(request);
            } else if (inputed_command.length == 2 && withArgs.containsKey(inputed_command[0])) {
                if (commandsWithArgs.contains(inputed_command[0])) {
                    try {
                        Long.parseLong(inputed_command[1]);
                    } catch (NumberFormatException e) {
                        return new Response ("Введите целое число");
                    }
                    if (Long.parseLong(inputed_command[1]) > 0 && CommandList.getCollection().stream().filter(person -> person.getId() == Long.parseLong(inputed_command[1])).count() > 0) {
                        return withArgs.get(inputed_command[0]).execute(request);
                    } else {
                        return new Response("Пользователя с таким ID нет");
                    }
                } else if (commandsWithLengthTwo.contains(inputed_command[0])) {
                    return withArgs.get(inputed_command[0]).execute(request);
                } else {
                    return new Response("Команда введена некорректно. Попробуйте ещё раз");
                }
            } else {
                return new Response("Такой команды не существует. Попробуйте ещё раз");
            }
        }  catch (SQLException e){
            return new Response("Ошибка во взаимодействии с базой данных");
        }
    }
}