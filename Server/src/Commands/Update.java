package Commands;

import DataBase.DBCollectionManager;
import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;
import OtherClasses.User;

import java.util.ArrayList;

/**
 * класс для комнады update
 */
public class Update extends Command {

    /**
     * обновляет значение элемента колекции, id которого равен заданному
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     */
    @Override
    public Response execute(Request request) {
        ArrayList<Person> toUpdate = new ArrayList<>();
        CommandList.getCollection().stream().filter(person -> person.getId() == request.getPerson().getId() && request.getUser().getUsername().equals(person.getCreator())).forEach(toUpdate::add);
        if (DBCollectionManager.update(request.getPerson(), request.getPerson().getId(), request.getUser()) ){
            CommandList.getCollection().removeAll(toUpdate);
            Person newPerson = request.getPerson();
            newPerson.setId(request.getPerson().getId());
            CommandList.getCollection().add((int) (request.getPerson().getId() - 1), newPerson);
            return new Response("Данные пользователя обновлены");
        } else{
            return new Response("Данные нельзя обновить");
        }
    }
}
