package Commands;

import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;

import java.util.ArrayList;
import java.util.Collections;

/**
 * класс для команды print_descending
 */
public class PrintDescending extends Command {


    /**
     * вывод элементов коллекции в порядке убывания
     * @param nothing запрос, полученный от клиента
     * @return
     */
    @Override
    public Response execute(Request nothing) {
        StringBuilder result = new StringBuilder();
        ArrayList<Person> reversedCollection = new ArrayList<>(CommandList.getCollection());
        Collections.sort(reversedCollection);
        Collections.reverse(reversedCollection);
        reversedCollection.forEach(person -> result.append(person).append("\n"));
        return new Response(result.toString());
    }
}
