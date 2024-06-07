package Commands;

import InputOutput.Response;
import OtherClasses.Request;

import java.sql.SQLException;

/**
 * шаблон команды
 */
public abstract class Command {
    /**
     * @param request запрос, полученный от клиента
     */
    public abstract Response execute(Request request) throws SQLException;
}
