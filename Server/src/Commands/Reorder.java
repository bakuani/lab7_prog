package Commands;

import InputOutput.Response;
import OtherClasses.Request;

import java.util.Collections;

/**
 * класс для команды reorder
 */
public class Reorder extends Command {


    /**
     * отсортировать коллекцию в порядке, обратном нынешнему
     * @param nothing запрос, полученный от клиента
     * @return
     */
    @Override
    public Response execute(Request nothing) {
        Collections.reverse(CommandList.getCollection());
        return new Response("Реорганизовано");
    }
}
