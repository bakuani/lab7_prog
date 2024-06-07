package Commands;

import DataBase.DBCollectionManager;
import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.sql.SQLException;


/**
 * класс, для команды add
 */
public class Add extends Command {


    /**
     * добавление элемента в коллекцию
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */
    @Override
    public Response execute(Request request) throws SQLException {

        DBCollectionManager.add(request.getPerson(), request.getUser().getUsername());
        CommandList.getCollection().add(request.getPerson());
        CommandList.getIDs().add(request.getPerson().getId());
        return new Response("Человек добавлен");
    }
}
