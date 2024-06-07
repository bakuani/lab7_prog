package Commands;

import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * класс для команды filter_starts_with_name
 */
public class FilterStartsWithName extends Command {
    /**
     * команда выводит элементы чьё имя начинается с заданной подстроки
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */
    @Override
    public Response execute(Request request) {
        String finalName = request.getCommand()[1].trim();
        StringBuilder result = new StringBuilder();
        CommandList.getCollection().stream().filter(person -> person.getName().startsWith(finalName)).forEach(person -> result.append(person).append("\n"));
        return new Response(result.toString());
    }
}
