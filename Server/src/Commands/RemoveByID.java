package Commands;

import DataBase.DBCollectionManager;
import DataBase.FromDB;
import DataBase.UserManager;
import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.util.ArrayList;

/**
 * класс для команды remove_by_id
 */
public class RemoveByID extends Command {


    /**
     * удаляет элемент из коллекции по его id
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     */
    @Override
    public Response execute(Request request) {
        FromDB fromDB = new FromDB(UserManager.getConnection());
        ArrayList<Person> toRemove = new ArrayList<>();
        if (DBCollectionManager.deleteWithID(request.getPerson().getId(), request.getUser().getUsername())) {
            CommandList.getCollection().stream().filter(person -> person.getId() == request.getPerson().getId() && request.getUser().getUsername().equals(person.getCreator())).forEach(person -> {
                toRemove.add(person);
            });
            DBCollectionManager.deleteWithID(request.getPerson().getId(), request.getUser().getUsername());
            CommandList.getCollection().removeAll(toRemove);
            if (toRemove.isEmpty()){
                return new Response("Пользователя с таким ID нет");
            } else{
                fromDB.loadCollection();

                return new Response("Удалено");
            }


        } else {
            return new Response("Данные не удалены");
        }
    }
}
