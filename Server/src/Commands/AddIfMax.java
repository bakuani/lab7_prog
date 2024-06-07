package Commands;

import DataBase.DBCollectionManager;
import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.sql.SQLException;
import java.util.Collections;

/**
 * класс для команды add_if_max
 */
public class AddIfMax extends Command {
    public static boolean mode = true;
    public static Long id;


    public static void setMode(boolean mode) {
        AddIfMax.mode = mode;
    }

    public static void setId(Long id) {
        AddIfMax.id = id;
    }
    String result = new String();




    /**
     * функция для add_if_max в интерактивном режиме
     */
    public void forScanner(Request request){
        if (request.getPerson().compareTo(Collections.max(CommandList.getCollection())) > 0) {
            DBCollectionManager.add(request.getPerson(), request.getUser().getUsername());
            CommandList.getCollection().add(request.getPerson());
            CommandList.getIDs().add(id);
            result += "Пользователь добавлен";
        } else {
            result += "Пользователь не добавлен";
        }
    }

    /**
     * функция для add_if_max  режиме чтения из файла
     *
     * @param
     */
    public void forFile(Request request) throws SQLException {
        if (request.getPerson().compareTo(Collections.max(CommandList.getCollection())) > 0) {
            CommandList.getCollection().add(request.getPerson());
            CommandList.getIDs().add(id);
            result += "Пользователь добавлен";
        } else {
            result += "Пользователь не добавлен";
        }
    }

    /**
     * функция выполнения add_if_max, добавляет новый объект в коллекцию, если он больше чем последний элемент коллекции
     *
     * @return ответ от сервера
     */
    @Override
    public Response execute(Request request) throws SQLException {
        if (mode == true) {
            forScanner(request);
        } else {
            forFile(request);
        }
        return new Response(result);
    }
}
