package Commands;

import DataBase.UserManager;
import InputOutput.Response;
import OtherClasses.Request;

import java.sql.SQLException;

/**
 * команда входа
 */
public class LogIn extends Command{

    /**
     * вход в аккаунт
     * @param request запрос, полученный от клиента
     * @return ответ от сервера
     * @throws SQLException
     */
    @Override
    public Response execute(Request request) throws SQLException {
        String[] credentials = request.getCommand()[1].split(",");
        return new Response(UserManager.logIn(credentials[0], credentials[1]).getMessage());
    }
}
