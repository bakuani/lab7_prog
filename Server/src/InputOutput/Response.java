package InputOutput;

import java.io.Serializable;

/**
 * ответ от сервера
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 5;
    private final String message;


    public Response(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
