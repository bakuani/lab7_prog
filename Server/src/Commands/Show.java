package Commands;

import InputOutput.Response;
import OtherClasses.Request;

import java.util.Arrays;

/**
 * класс для команды show
 */
public class Show extends Command {

    /**
     * выводит содержимое коллекции
     * @param nothing запрос, полученный от клиента
     * @return ответ от сервера
     */
    @Override
    public Response execute(Request nothing) {
        StringBuilder result = new StringBuilder();
        CommandList.getCollection().forEach(person -> result.append(person).append('\n'));

        return new Response(result.toString());
    }
}
