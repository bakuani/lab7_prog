import Commands.CommandList;
import DataBase.CredentialsFileReader;
import DataBase.DBConnection;
import DataBase.FromDB;
import DataBase.UserManager;
import InputOutput.RequestReceiver;
import OtherClasses.Person;

import java.sql.Connection;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Некорректно введены данные для запуска");
        }
        Class.forName("org.postgresql.Driver");
        CredentialsFileReader logIn = new CredentialsFileReader();
        String[] credentials = logIn.getCredentials(args[0]).split(" ");
        DBConnection dbConnection = new DBConnection("jdbc:postgresql://localhost:5432/studs", credentials[0], credentials[1]);
        Connection connection = dbConnection.connectToDB();
        FromDB fromDB = new FromDB(connection);
        CommandList.setCollection((CopyOnWriteArrayList<Person>) fromDB.loadCollection().clone());
        UserManager addUser = new UserManager(connection);
        addUser.loadUsers();
        RequestReceiver requestReceiver = new RequestReceiver();
        requestReceiver.requestReceiver();
    }
}
