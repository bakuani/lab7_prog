package Commands;

import OtherClasses.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * класс с коллекцией объектов и списком команд
 */
public class CommandList {
    private static CopyOnWriteArrayList<Person> collection = new CopyOnWriteArrayList<>();

    public static void setCollection(CopyOnWriteArrayList<Person> collection) {
        CommandList.collection = collection;
    }

    public static ArrayList<Long> getIDs() {
        return IDs;
    }

    private static ArrayList<Long> IDs = new ArrayList<>();
    private ArrayList<String> commands = new ArrayList<>(List.of("help", "info", "show", "add", "update", "remove_by_id", "clear", "save", "execute_script", "exit", "add_if_max", "remove_lower", "reorder", "filter_starts_with_name", "print_descending", "print_unique_passport_i_d", "login", "register"));

    public ArrayList<String> getCommands() {
        return commands;
    }

    public static CopyOnWriteArrayList<Person> getCollection() {
        return collection;
    }
}
