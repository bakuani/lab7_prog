package Commands;

import DataBase.DBCollectionManager;
import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.sql.SQLException;
import java.util.Objects;

/**
 * класс для команды clear
 */
public class Clear extends Command {

    /**
     * очистка коллекции
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */
    @Override
    public Response execute(Request request) throws SQLException {

        DBCollectionManager.clear(request.getUser().getUsername());
        CommandList.getCollection().removeIf(person -> Objects.equals(person.getCreator(), request.getUser().getUsername()));
        return new Response("Коллекция очищена");
    }
}
