package Commands;

import InputOutput.Response;
import OtherClasses.Person;
import OtherClasses.Request;

import java.util.HashSet;

/**
 * класс для команды print_unique_passport_i_d
 */
public class PrintUniquePassportID extends Command {


    /**
     * выводит уникальные passportID всех элементов коллекции
     * @param nothing запрос, полученный от клиента
     * @return ответ от сервера
     */
    @Override
    public Response execute(Request nothing) {
        StringBuilder result = new StringBuilder();
        HashSet<String> unique_passportID = new HashSet<>();
        CommandList.getCollection().forEach(person -> unique_passportID.add(person.getPassportID()));

        unique_passportID.stream().forEach(s -> result.append(s).append(","));

        return new Response(result.toString());
    }
}