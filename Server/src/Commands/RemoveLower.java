package Commands;

import DataBase.DBCollectionManager;
import DataBase.FromDB;
import DataBase.UserManager;
import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class RemoveLower extends Command {
    private FromDB fromDB = new FromDB(UserManager.getConnection());
    public static boolean mode = true;
    public static long id;

    public static void setMode(boolean mode) {
        RemoveLower.mode = mode;
    }

    public static void setId(long id) {
        RemoveLower.id = id;
    }


    /**
     * функция для выполнения команды в интерактивном режиме
     * @param request запрос
     */
    public void forScanner(Request request) {
        ArrayList<Person> toRemove = new ArrayList<>();
        CommandList.getCollection().stream().filter(person -> person.compareTo(request.getPerson()) < 0 && request.getUser().getUsername().equals(person.getCreator())).forEach(person -> {toRemove.add(person);
        });
        DBCollectionManager.deleteWhere(request.getPerson().getName(), request.getUser().getUsername());
        CommandList.getCollection().removeAll(toRemove);


    }

    /**
     * функция для выполнения команды из файла
     * @param request запрос
     */
    public void forFile(Request request) {
        ArrayList<Person> toRemove = new ArrayList<>();
        CommandList.getCollection().stream().filter(person -> person.compareTo(request.getPerson()) < 0 && request.getUser().getUsername().equals(person.getCreator())).forEach(person -> {toRemove.add(person);
        });
        DBCollectionManager.deleteWhere(request.getPerson().getName(), request.getUser().getUsername());
        CommandList.getCollection().removeAll(toRemove);
    }


    /**
     * функция для выполнения команды remove_lower, удаляет все элементы меньшие, чем заданный
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */
    @Override
    public Response execute(Request request) throws SQLException {
        if (mode == true) {
            forScanner(request);
        } else{
            forFile(request);
        }
        fromDB.loadCollection();
        return new Response("Пользователи удалены");
    }
}