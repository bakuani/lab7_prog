package InputOutput;

import Commands.*;
import OtherClasses.Person;
import OtherClasses.User;

import java.util.HashMap;

/**
 * класс с HashMap для команд с аргументами и без
 */
public class HashMaps {

    private static final HashMap<String, Command> withoutArgs = new HashMap<>();
    private static final HashMap<String, Command> withArgs = new HashMap<>();

    public static HashMap<String, Command> getWithoutArgs() {
        return withoutArgs;
    }

    public static HashMap<String, Command> getWithArgs() {
        return withArgs;
    }

    /**
     * функция с запуском команд
     */
    public void work() {
        withoutArgs.put("help", new Help());
        withoutArgs.put("add", new Add());
        withoutArgs.put("clear", new Clear());
        withoutArgs.put("info", new Info());
        withoutArgs.put("show", new Show());
        withoutArgs.put("add_if_max", new AddIfMax());
        withoutArgs.put("remove_lower", new RemoveLower());
        withoutArgs.put("reorder", new Reorder());
        withoutArgs.put("print_descending", new PrintDescending());
        withoutArgs.put("print_unique_passport_i_d", new PrintUniquePassportID());
        withArgs.put("execute_script", new ExecuteScript());
        withArgs.put("update", new Update());
        withArgs.put("filter_starts_with_name", new FilterStartsWithName());
        withArgs.put("remove_by_id", new RemoveByID());
        withArgs.put("login", new LogIn());
        withArgs.put("register", new Registration());
    }
}