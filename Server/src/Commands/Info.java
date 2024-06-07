package Commands;

import DataBase.DBCollectionManager;
import InputOutput.Response;
import OtherClasses.Request;

import java.sql.SQLException;
import java.util.Date;


/**
 * класс для команды info
 */
public class Info extends Command {


    /**
     * вывод информации о коллекции
     * @param nothing запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */
    @Override
    public Response execute(Request nothing) throws SQLException {
        String result = "Тип коллекции: ArrayList\n";
        if (CommandList.getCollection().size() == 0) {
            result += "Количество элементов в коллекции: 0\n";
        } else {
            result += "Количество элементов в коллекции: %s \n".formatted(CommandList.getCollection().size());
        }
        Date date = new Date();
        result += "Дата инициализации коллекции: %tc\n".formatted(date);
        return new Response(result);
    }
}
