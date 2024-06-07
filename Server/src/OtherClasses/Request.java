package OtherClasses;

import java.io.Serializable;
import java.util.Arrays;

/**
 * запрос
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 4;
    private Person person;
    private String[] command;
    private User user;

    public User getUser() {
        return user;
    }

    public Request(String[] command, Person person, User user) {
        this.command = command;
        this.person = person;
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public String[] getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return  Arrays.toString(command) + " " + person;
    }
}