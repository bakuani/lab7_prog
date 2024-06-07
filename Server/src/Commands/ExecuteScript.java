package Commands;

import InputOutput.Response;
import OtherClasses.Request;

import java.sql.SQLException;

/**
 * класс для команды execut_script
 */
public class ExecuteScript extends Command {
    /**
     * команда получает команды и данные из файла
     *
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */

    @Override
    public Response execute(Request request) {
        ForScript forScript = new ForScript();
        return forScript.forScript(request);
    }
}